package com.afkl.travel.exercise.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Order(1)
	@Configuration
	public static class ApiSecurityConfig extends WebSecurityConfigurerAdapter {

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.authorizeRequests().antMatchers("/, /**").permitAll().and()
					.antMatcher("${service.locations.url}/**").httpBasic().and()
					.authorizeRequests().anyRequest().hasRole("USER")
					.anyRequest().authenticated()
					.and().csrf().disable();
		}

		@Override
		@Bean
		public UserDetailsService userDetailsService() {
			String username = "someuser";
			String password = "psw";

			// Set the inMemoryAuthentication object with the given credentials:
			InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
			String encodedPassword = passwordEncoder().encode(password);
			manager.createUser(User.withUsername(username).password(encodedPassword).roles("USER").build());
			return manager;
		}

		@Bean
		public PasswordEncoder passwordEncoder() {
			return new BCryptPasswordEncoder();
		}
	}
}