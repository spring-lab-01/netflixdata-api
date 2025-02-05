package com.netflixdata.api.security.apikey;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import java.io.IOException;

public class ApiKeyAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    private final String apiKeyHeader;

    public ApiKeyAuthenticationFilter(String apiKeyHeader, AuthenticationManager authenticationManager, String urlPattern) {
        super(urlPattern); // Filter all requests
        this.apiKeyHeader = apiKeyHeader;
        setAuthenticationManager(authenticationManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        String apiKey = request.getHeader(apiKeyHeader);
        if (apiKey == null) {
            throw new AuthenticationServiceException("API key not provided");
        }

        ApiKeyAuthentication authRequest = new ApiKeyAuthentication(apiKey, AuthorityUtils.NO_AUTHORITIES);
        return getAuthenticationManager().authenticate(authRequest);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        super.successfulAuthentication(request, response, chain, authResult);
        chain.doFilter(request, response);
    }
}
