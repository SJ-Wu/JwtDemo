package com.example.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebFilter
public class JwtFilter extends OncePerRequestFilter {
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String path = request.getServletPath();
        boolean loginPath = "/token".equals(path);
        return loginPath;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        String bearer = "Bearer";
        System.out.println(authorHeader);
        if (authorHeader != null && authorHeader.startsWith(bearer)) {
            try {
                String token = authorHeader.substring(bearer.length());
                Claims claims = Jwts.parser().setSigningKey("secretKey").parseClaimsJws(token).getBody();

                System.out.println("JWT payload:" + claims.toString());

                filterChain.doFilter(request, response);

            } catch (Exception e) {
                System.err.println("Error : " + e);
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);

                Map<String, String> err = new HashMap<>();
                err.put("jwt_err", e.getMessage());
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), err);
            }
        } else {
            System.out.println("SC_UNAUTHORIZED");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }
}
