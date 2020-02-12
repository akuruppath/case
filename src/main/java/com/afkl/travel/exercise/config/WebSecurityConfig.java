package com.afkl.travel.exercise.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

	@Order(1)
	@Configuration
	public static class ApiSecurityConfig extends WebSecurityConfigurerAdapter {

		@Value("${service.locations.url}")
		private String locationsUrl;

		@Value("${service.security.locations.user}")
		private String locationsUser;

		@Value("${service.security.locations.pwd}")
		private String locationsPwd;
		
		@Value("${service.security.locations.role}")
		private String locationsRole;

		@Autowired
		private PasswordEncoder passwordEncoder;

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.authorizeRequests().antMatchers("/, /**").permitAll().and().antMatcher(locationsUrl + "/**")
					.httpBasic().and().authorizeRequests().anyRequest().hasRole(locationsRole).anyRequest().authenticated()
					.and().csrf().disable();
		}

		@Override
		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
			String encodedPassword = passwordEncoder.encode(locationsPwd);
			auth.inMemoryAuthentication().withUser(locationsUser).password(encodedPassword).roles(locationsRole);
		}

	}

	@Order(2)
	@Configuration
	public static class ActuatorSecurity extends WebSecurityConfigurerAdapter {

		@Value("${service.security.actuator.user}")
		private String actuatorUser;

		@Value("${service.security.actuator.pwd}")
		private String actuatorPwd;

		@Value("${service.actuator.url}")
		private String actuatorUrl;
		
		@Value("${service.security.actuator.role}")
		private String actuatorRole;

		@Autowired
		private PasswordEncoder passwordEncoder;

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.authorizeRequests().antMatchers("/, /**").permitAll().and().antMatcher(actuatorUrl + "/**").httpBasic()
					.and().authorizeRequests().anyRequest().hasRole(actuatorRole).anyRequest().authenticated().and().csrf()
					.disable();
		}

		@Override
		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
			String encodedPassword = passwordEncoder.encode(actuatorPwd);
			auth.inMemoryAuthentication().withUser(actuatorUser).password(encodedPassword).roles(actuatorRole);
		}

	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}