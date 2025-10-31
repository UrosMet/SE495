package com.metropolitan.SE495.configuration.security;

import com.metropolitan.SE495.jwt.filters.JwtAuthenticationFilter;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import java.util.List;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class WebSecurityConfig {

    private AuthenticationProvider authenticationProvider;
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(
                        cors -> cors.configurationSource(request -> {
                            CorsConfiguration corsConfiguration = new CorsConfiguration();
                            corsConfiguration.setAllowedOrigins(List.of("http://localhost:4200"));
                            corsConfiguration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE"));
                            corsConfiguration.setAllowedHeaders(List.of("Authorization", "Cache-Control", "Content-Type"));
                            corsConfiguration.setAllowCredentials(true);
                            return corsConfiguration;
                        })
                )
                .sessionManagement(sessionMangConfig -> sessionMangConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(
                        authConfig-> authConfig
                                .requestMatchers(HttpMethod.POST, "/auth/login", "/auth/register").permitAll()
                                .requestMatchers(HttpMethod.GET, "/products/**", "/subscription/info", "/images/**").permitAll()
                                .requestMatchers("/error").permitAll()

                                //USER endpoints
                                .requestMatchers(HttpMethod.GET, "/order/**", "/shipment/**", "/payment/**", "/user/profile", "/subscription/**").hasRole("USER")
                                .requestMatchers(HttpMethod.POST, "/subscription/**", "/order/**").hasRole("USER")
                                .requestMatchers(HttpMethod.PUT, "/subscription/**", "/user/**", "/address/**").hasRole("USER")
                                .requestMatchers(HttpMethod.DELETE, "/subscription/**").hasRole("USER")
                                .requestMatchers(HttpMethod.POST, "/auth/logout").hasRole("USER")

                                // ADMIN endpoints
                                .requestMatchers("/admin/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.GET, "/user/**", "/report/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.POST, "/product/**", "/payment/**", "/user/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.PUT, "/product/**", "/subscription/**", "/order/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.DELETE, "/product/**", "/user/**").hasRole("ADMIN")

                                // Fallback: sve ostalo traži login
                                .anyRequest().authenticated());
        return http.build();
    }
}
