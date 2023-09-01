package org.n52.kommonitor.security;

import org.n52.kommonitor.dataloader.ShapeFileDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.session.NullAuthenticatedSessionStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SecurityConfig {

    //@Value("${spring.security.oauth2.resourceserver.jwt.jwk-set-uri}")
    //String jwkSetUri;

    /**
     * Defines the session authentication strategy.
     */
    @Bean
    protected SessionAuthenticationStrategy sessionAuthenticationStrategy() {
        return new NullAuthenticatedSessionStrategy();
    }

    @Bean
    @ConditionalOnProperty(
            value="kommonitor.processor.security.enabled",
            havingValue = "true",
            matchIfMissing = false)
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.csrf().disable();
        http.authorizeRequests()
            .antMatchers("/").permitAll()
            .antMatchers("/swagger-ui/*").permitAll()
            .antMatchers("/v3/api-docs/swagger-config").permitAll()
            .antMatchers("/specs/**").permitAll()
            .antMatchers("/**").authenticated()
            .anyRequest().denyAll();

        http.oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);
        return http.build();
    }

    @Bean
    @ConditionalOnProperty(
            value="kommonitor.processor.security.enabled",
            havingValue = "false",
            matchIfMissing = false)
    SecurityFilterChain unsecuredFilterChain(final HttpSecurity http) throws Exception {
        return http.csrf()
                .disable()
                .authorizeRequests()
                .anyRequest()
                .permitAll().and().build();
    }
    
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("*")
                        .allowedHeaders("*")
                        .allowedMethods("*");
//                        .allowCredentials(true);
            }
        };
    }

    /*
    @Bean
    JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder.withJwkSetUri(this.jwkSetUri).build();
    }

     */
}
