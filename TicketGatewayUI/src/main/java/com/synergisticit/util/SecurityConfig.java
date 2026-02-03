package com.synergisticit.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	    http
	        .csrf(csrf -> csrf.disable())
	        .authorizeHttpRequests(auth -> auth
	            // 1. Explicitly permit the login and register URLs
	            .requestMatchers("/login", "/register", "/css/**", "/js/**").permitAll()
	            // 2. IMPORTANT: Permit internal JSP forwards (Common in Spring Boot 3+)
	            .requestMatchers("/WEB-INF/views/**").permitAll() 
	            .anyRequest().authenticated()
	        )
	        .formLogin(form -> form
	            .loginPage("/login") // This must match your GetMapping
	            .loginProcessingUrl("/api/users/auth/login")
	            .defaultSuccessUrl("/dashboard", true)
	            .permitAll()
	        )
	        .logout(logout -> logout.permitAll());
	    
	    return http.build();
	}
	

}