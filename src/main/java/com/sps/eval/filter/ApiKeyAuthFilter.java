package com.sps.eval.filter;


import com.sps.eval.service.auth.AuthenticationService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.autoconfigure.observation.ObservationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collection;

@Component
public class ApiKeyAuthFilter extends OncePerRequestFilter {

    @Value("${api.key}")
    private String apiKey;
    @Value("${api.secret}")
    private String apiSecret;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // Get the API key and secret from request headers
        String requestApiKey = request.getHeader("X-API-KEY");
        //String requestApiSecret = request.getHeader("X-API-SECRET");
        // Validate the key and secret

        //if (apiKey.equals(requestApiKey) && apiSecret.equals(requestApiSecret)) {
        if(apiKey.equals(requestApiKey)) {
            // Continue processing the request
            //filterChain.doFilter(request, response);

            Authentication authentication = AuthenticationService.getAuthentication((HttpServletRequest) request);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);

        /*
        if (apiKey.equals(requestApiKey) && apiSecret.equals(requestApiSecret)) {
            // Continue processing the request
            filterChain.doFilter(request, response);
        } else {
            // Reject the request and send an unauthorized error
            //response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setStatus(HttpStatus.NOT_ACCEPTABLE.value());
            response.getWriter().write("Unauthorized");
        }

         */


        //filterChain.doFilter(request, response);
    }

}