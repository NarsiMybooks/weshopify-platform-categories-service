package com.weshopifyapp.features.categories.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class AppSecurityConfig {

	@Autowired
	private PasswordEncoder passswordenc;
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.authorizeHttpRequests(request->{
			try {
				request
				     .anyRequest()
				     .authenticated()
				     .and()
				     .cors()
				     .disable()
				     .csrf()
				     .disable()
				     .anonymous()
				     .disable()
				     .httpBasic();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		return httpSecurity.build();
	}
	
	@Bean
	public InMemoryUserDetailsManager userDetails() {
		UserDetails userDetails = User.
				withUsername("admin")
				.password(passswordenc.encode("admin"))
				.roles("admin").build();
		return new InMemoryUserDetailsManager(userDetails);
	}
	
	@Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
		log.info("ingnoring the security");
		return (web) -> web.ignoring()
				.requestMatchers("/swagger-ui.html","/swagger-ui/**","/v3/api-docs/**");
    }
	
}
