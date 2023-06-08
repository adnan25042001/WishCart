package com.wishcart.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.wishcart.auth.service.LogoutService;

import static com.wishcart.model.Role.ADMIN;
import static com.wishcart.model.Role.USER;
import static com.wishcart.model.Role.SELLER;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

	// JWT authentication filter bean
	private final JwtAuthenticationFilter jwtAuthFilter;

	// Authentication provider
	private final AuthenticationProvider authenticationProvider;

	// Logout handler class
	private final LogoutService logoutService;

	// Create security filter bean
	@SuppressWarnings("removal")
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		http
			.csrf(AbstractHttpConfigurer::disable)
			.cors(Customizer.withDefaults())
			.authorizeHttpRequests()
			.requestMatchers(
					"/api/v1/auth/**",
					"/v2/api-docs", 
					"/v3/api-docs", 
					"/v3/api-docs/**",
					"/swagger-resources", 
					"/swagger-resources/**", 
					"/configuration/ui", 
					"configuration/security",
					"swagger-ui/**", 
					"webjars/**", 
					"/swagger-ui.html"
					)
			.permitAll()
			.requestMatchers("/api/v1/admin/**")
			.hasRole(ADMIN.name())
			.requestMatchers("/api/v1/seller/**")
			.hasRole(SELLER.name())
			.requestMatchers("/api/v1/user/**")
			.hasRole(USER.name())
			.anyRequest()
			.authenticated()
			.and()
			.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
			.authenticationProvider(authenticationProvider)
			.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
			.logout()
			.logoutUrl("/api/v1/auth/logout")
			.addLogoutHandler(logoutService)
			.logoutSuccessHandler(
				(request, response, authentication) -> 
					SecurityContextHolder.clearContext()
			);

		return http.build();
		
	}

}
