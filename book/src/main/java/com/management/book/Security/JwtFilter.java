package com.management.book.Security;

import com.management.book.Services.TokenBlackListService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final TokenBlackListService tokenBlackListService;
    public JwtFilter(JwtUtil jwtUtil, TokenBlackListService tokenBlackListService) {
        this.jwtUtil = jwtUtil;
        this.tokenBlackListService = tokenBlackListService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)

        throws ServletException, IOException{
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            filterChain.doFilter(request, response);
            return;
        }

        String authHeader = request.getHeader("Authorization");

        if(authHeader != null && authHeader.startsWith("Bearer ")){
            String token = authHeader.substring(7);
            // Check whether the incoming JWT token has been blacklisted (revoked/logout token)
            if (tokenBlackListService.isTokenBlacklisted(token)) {
                // If the token is blacklisted, mark the request as unauthorized (HTTP 401)
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                // Send an error message back to the client explaining why access is denied
                response.getWriter().write("Token is blacklisted. Please login again.");

                // Stop further processing of the request
                // - Controller will NOT be executed
                // - Protected resources will NOT be accessed
                return; // Stop the request right here
            }

            Claims claims = jwtUtil.extractClaims(token);
            String username = claims.getSubject();
            String role = claims.get("role", String.class);

            SecurityContextHolder.getContext().setAuthentication(
                    new UsernamePasswordAuthenticationToken(
                            username, null,
                            List.of(new SimpleGrantedAuthority(role)))
            );
        }
        filterChain.doFilter(request, response);
    }
}
