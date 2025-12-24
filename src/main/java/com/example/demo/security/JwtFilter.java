package com.example.demo.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

@Component
public class JwtFilter extends OncePerRequestFilter {
    
    private final JwtUtil jwtUtil;
    
    public JwtFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, 
                                   FilterChain filterChain) throws ServletException, IOException {
        
        final String authorizationHeader = request.getHeader("Authorization");
        
        String token = null;
        String email = null;
        
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            token = authorizationHeader.substring(7);
            try {
                email = jwtUtil.extractEmail(token);
            } catch (Exception e) {
                // Token is invalid, continue without authentication
            }
        }
        
        if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            if (!jwtUtil.isTokenExpired(token)) {
                String role = jwtUtil.extractRole(token);
                
                UsernamePasswordAuthenticationToken authToken = 
                    new UsernamePasswordAuthenticationToken(email, null, 
                        Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + role)));
                
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        
        filterChain.doFilter(request, response);
    }
    
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getServletPath();
        return path.startsWith("/api/auth/") || 
               path.startsWith("/h2-console/") ||
               path.equals("/api/users/register") ||
               path.equals("/api/users/login");
    }
}