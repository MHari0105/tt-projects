package com.myproject.hotelmanagementsystem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf(csrf -> csrf
                        .disable())
                        .authorizeHttpRequests(requests -> requests
                            .requestMatchers("/api/auth/**").permitAll()
                            .requestMatchers("/api/guest/get",
                                            "/api/guest/get/**",
                                            "/api/guest/delete/**",
                                            "/api/guest/update/**",
                                            "/api/reserve/get")
                            .hasAuthority("ADMIN")

                            .requestMatchers("/api/room/post",
                                            "/api/room/get",
                                            "/api/room/put/**")
                            .hasAuthority("MANAGER")

                            .requestMatchers("/api/room/available",
                                            "/api/guest/post")
                            .hasAnyAuthority("GUEST", "MANAGER", "ADMIN")

                            .requestMatchers("/api/reserve/post",
                                            "/api/reserve/close")
                            .hasAnyAuthority("ADMIN", "MANAGER")
                        .anyRequest().authenticated())
                        .sessionManagement(management -> management
                            .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                        .authenticationProvider(authProvider)
                        .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        
        return http.build();
    }
    
}
