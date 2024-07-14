package com.challenge.forohub.security;

import com.challenge.forohub.repository.LoginRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {
    @Autowired
    private TokenService tokenService;
    @Autowired
    private LoginRepository loginRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        // If the endpoint ist "login" don't apply this filter
        if (request.getServletPath().equals("/login")) {
            filterChain.doFilter(request, response);
            return;
        }
        String token = request.getHeader("Authorization");
        // If the token passed on the Headers is not null
        if (token != null && token.startsWith("Bearer ")){
            // Clean the token
            token = token.replace("Bearer ", "");
            // Verify the token and get the username
            String username = tokenService.getSubject(token);
            // if the token's username exists
            if (username != null){
                // Create an active user session
                UserDetails user = loginRepository.findByUsername(username);
                // Authenticate the user
                Authentication authentication = new UsernamePasswordAuthenticationToken(
                        user, null, user.getAuthorities());
                // Pass the authenticated session to Spring Security context
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        // Call the next filter
        filterChain.doFilter(request, response);
    }
}
