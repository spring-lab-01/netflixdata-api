package com.netflixdata.api.security.apikey;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Component;

@Component
public class ApiKeyAuthenticationProvider implements AuthenticationManager {

    private static final String API_KEY = "random0000ApiKey";

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String apiKey = (String) authentication.getPrincipal();

        // Validate the API key against your data store
        if (!isValidApiKey(apiKey)) {
            throw new BadCredentialsException("Invalid API key");
        }

        return new ApiKeyAuthentication(apiKey, AuthorityUtils.createAuthorityList("API_ACCESS"));
    }

    private boolean isValidApiKey(String apiKey) {
        // Implement your API key validation logic here
        return API_KEY.equals(apiKey);
    }
}