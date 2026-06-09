# ---- Base Maven build ----
FROM maven:3-openjdk-17 as build
WORKDIR /app

# Cache dependencies as long as the POM changes
COPY ./pom.xml ./pom.xml
RUN mvn -f ./pom.xml dependency:go-offline --fail-never

# Copy source files for build
COPY src ./src

# Copy entrypoint.sh script
COPY entrypoint.sh /app/kommonitor-spatial-data-processor/entrypoint.sh

# Run the Maven build
RUN mvn -f ./pom.xml clean install -Dapp.finalName=kommonitor-spatial-data-processor-app -DskipTests

# ---- Run the application ----
FROM eclipse-temurin:17.0.11_9-jdk-alpine
WORKDIR /app

# Copy from the base build image
COPY --from=build app/target/kommonitor-spatial-data-processor-*.jar /app/kommonitor-spatial-data-processor-app.jar

# Copy the modified entrypoint.sh
COPY --from=build --chmod=755 app/kommonitor-spatial-data-processor/entrypoint.sh /__cacert_entrypoint.sh
ENTRYPOINT ["/__cacert_entrypoint.sh"]

# Set the entrypoint for starting the app
CMD ["sh", "-c", "java ${JAVA_OPTS} -jar /app/kommonitor-spatial-data-processor-app.jar"]
