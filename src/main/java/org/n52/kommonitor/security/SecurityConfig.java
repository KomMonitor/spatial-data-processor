package org.n52.kommonitor.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.session.NullAuthenticatedSessionStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

@Configuration
public class SecurityConfig {

    //@Value("${spring.security.oauth2.resourceserver.jwt.jwk-set-uri}")
    //String jwkSetUri;

    @Value("${kommonitor.spatial-data-processor.allowed-cors-origins}")
    private String[] allowedOrigins;

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
        http
                .sessionManagement(sessions -> sessions.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .csrf(AbstractHttpConfigurer::disable)
                .cors(cors -> corsConfigurationSource())
                .authorizeRequests((requests) -> requests
                        .requestMatchers(new AntPathRequestMatcher("/")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/swagger-ui/*")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/swagger-ui.html")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/v3/api-docs")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/v3/api-docs/swagger-config")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/specs/**")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/**")).authenticated()
                        .anyRequest().denyAll()
                )
                .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);
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

    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList(allowedOrigins));
        configuration.setAllowedMethods(List.of("*"));
        configuration.setAllowedHeaders(List.of("*"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

}
