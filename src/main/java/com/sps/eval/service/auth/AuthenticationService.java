package com.sps.eval.service.auth;

import com.sps.eval.auth.ApiKeyAuthentication;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {


    @Value("${api.key.name}")
    private String name;

    private static String NAME_STATIC;

    @Value("${api.key.name}")
    public void setNameStatic(String name){
        AuthenticationService.NAME_STATIC = name;
    }

    @Value("${api.key.value}")
    private String value;

    private static String VALUE_STATIC;

    @Value("${api.key.value}")
    public void setValueStatic(String value){
        AuthenticationService.VALUE_STATIC = value;
    }

    public static Authentication getAuthentication(HttpServletRequest request) {
        String apiKey = request.getHeader(NAME_STATIC);
        if (apiKey == null || !apiKey.equals(VALUE_STATIC)) {
            throw new BadCredentialsException("Invalid API Key");
        }

        return new ApiKeyAuthentication(apiKey, AuthorityUtils.NO_AUTHORITIES);
    }
}
