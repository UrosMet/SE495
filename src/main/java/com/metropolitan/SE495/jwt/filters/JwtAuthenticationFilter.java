package com.metropolitan.SE495.jwt.filters;


import com.metropolitan.SE495.authentication.TokenBlackListService;
import com.metropolitan.SE495.entity.User;
import com.metropolitan.SE495.jwt.JwtService;
import com.metropolitan.SE495.repository.UserRepository;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.io.IOException;

@Component
@AllArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private JwtService jwtService;
    private UserRepository userRepository;
    private TokenBlackListService tokenBlackListService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, JwtException {
        try {
            String authHeader = request.getHeader("Authorization"); // Bearer jwt
            if(authHeader == null || !authHeader.startsWith("Bearer ")){
                filterChain.doFilter(request, response);
                return;
            }
            String jwt = authHeader.split(" ")[1];

            if (tokenBlackListService.isTokenBlacklisted(jwt)) {
                extracted(response, "Please login - Token blacklisted");
                return;
            }
            String email = jwtService.extractUsername(jwt);
            User user = userRepository.findByEmail(email).get();
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                    email, null, user.getAuthorities()
            );
            SecurityContextHolder.getContext().setAuthentication(authToken);
            filterChain.doFilter(request, response);

        }
        catch (JwtException e) {
            System.err.println(e.getMessage());
            extracted(response, e.getMessage());
        }catch (Exception e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    private static void extracted(HttpServletResponse response , String s) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        response.getWriter().write("{\"error\": \""+s+"\"}");
        response.getWriter().flush();
        response.getWriter().close();
    }
}
