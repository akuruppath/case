package com.afkl.travel.exercise.config;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	private static final String URL = "url";
	private static final String USER = "user";
	private static final String PWD = "pwd";
	private static final String ROLE = "role";

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Order(1)
	@Configuration
	public static class ApiSecurityConfig extends WebSecurityConfigurerAdapter {

		private final PasswordEncoder passwordEncoder;
		private final Map<String, String> locationsMap;

		@Autowired
		public ApiSecurityConfig(PasswordEncoder passwordEncoder, ApplicationConfiguration appConfig) {
			this.passwordEncoder = passwordEncoder;
			this.locationsMap = appConfig.getLocations();
		}

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.authorizeRequests().antMatchers("/, /**").permitAll().and().antMatcher(locationsMap.get(URL) + "/**")
					.httpBasic().and().authorizeRequests().anyRequest().hasRole(locationsMap.get(ROLE)).anyRequest()
					.authenticated().and().csrf().disable();
		}

		@Override
		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
			String encodedPassword = passwordEncoder.encode(locationsMap.get(PWD));
			auth.inMemoryAuthentication().withUser(locationsMap.get(USER)).password(encodedPassword)
					.roles(locationsMap.get(ROLE));
		}

	}

	@Order(2)
	@Configuration
	public static class ActuatorSecurity extends WebSecurityConfigurerAdapter {

		private final PasswordEncoder passwordEncoder;
		private final Map<String, String> actuatorMap;

		@Autowired
		public ActuatorSecurity(PasswordEncoder passwordEncoder, ApplicationConfiguration appConfig) {
			this.passwordEncoder = passwordEncoder;
			this.actuatorMap = appConfig.getActuator();
		}

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.authorizeRequests().antMatchers("/, /**").permitAll().and().antMatcher(actuatorMap.get(URL) + "/**")
					.httpBasic().and().authorizeRequests().anyRequest().hasRole(actuatorMap.get(ROLE)).anyRequest()
					.authenticated().and().csrf().disable();
		}

		@Override
		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
			String encodedPassword = passwordEncoder.encode(actuatorMap.get(PWD));
			auth.inMemoryAuthentication().withUser(actuatorMap.get(USER)).password(encodedPassword)
					.roles(actuatorMap.get(ROLE));
		}

	}

}