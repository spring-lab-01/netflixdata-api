package com.netflixdata.api.security.apikey;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@ConditionalOnProperty(prefix = "auth", name="type", havingValue = "apiKey")
public class ApiKeySecurityConfig {

    private static final String API_KEY_HEADER_NAME = "X-API-KEY";

    private final ApiKeyAuthenticationProvider apiKeyAuthenticationProvider;

    public ApiKeySecurityConfig(ApiKeyAuthenticationProvider apiKeyAuthenticationProvider) {
        this.apiKeyAuthenticationProvider = apiKeyAuthenticationProvider;
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authz ->
                        authz.requestMatchers("/swagger-ui/**","/v3/api-docs/**","/ping").permitAll()
                                .requestMatchers("/api/**").hasAuthority("API_ACCESS")
                        .anyRequest().authenticated())
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(new ApiKeyAuthenticationFilter(API_KEY_HEADER_NAME, apiKeyAuthenticationProvider, "/api/**"),
                        org.springframework.security.web.authentication.www.BasicAuthenticationFilter.class)
                .authenticationManager(apiKeyAuthenticationProvider);
        return http.build();
    }


    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components().addSecuritySchemes("apiKey", getApiKeySecurityScheme()))
                .addSecurityItem(new SecurityRequirement().addList("apiKey"));
    }

    private static SecurityScheme getApiKeySecurityScheme() {
        return new SecurityScheme()
                .type(SecurityScheme.Type.APIKEY)
                .scheme("apiKey")
                .in(SecurityScheme.In.HEADER)
                .name("X-API-KEY");
    }


}
