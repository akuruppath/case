package com.afkl.travel.exercise.config;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import com.afkl.travel.exercise.utils.constants.Constants;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	private static final String[] ALLOWED_URLS = new String[] { "/, /**" };

	private final Map<String, String> locationsMap;
	private final Map<String, String> actuatorMap;

	@Autowired
	public WebSecurityConfig(ApplicationConfiguration appConfig) {
		this.locationsMap = appConfig.getLocations();
		this.actuatorMap = appConfig.getActuator();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests().antMatchers(ALLOWED_URLS).permitAll()
				.antMatchers(locationsMap.get(Constants.URL) + "/**").hasRole(locationsMap.get(Constants.ROLE))
				.antMatchers(actuatorMap.get(Constants.URL) + "/**").hasRole(actuatorMap.get(Constants.ROLE))
				.anyRequest().authenticated().and().httpBasic().and().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}

	@Bean
	@Override
	public UserDetailsService userDetailsService() {

		UserDetails theUser = User.withUsername(locationsMap.get(Constants.USER_NAME))
				.passwordEncoder(PasswordEncoderFactories.createDelegatingPasswordEncoder()::encode)
				.password(locationsMap.get(Constants.PASSWORD)).roles(locationsMap.get(Constants.ROLE)).build();

		UserDetails theManager = User.withUsername(actuatorMap.get(Constants.USER_NAME))
				.passwordEncoder(PasswordEncoderFactories.createDelegatingPasswordEncoder()::encode)
				.password(actuatorMap.get(Constants.PASSWORD)).roles(actuatorMap.get(Constants.ROLE)).build();

		InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();

		userDetailsManager.createUser(theUser);
		userDetailsManager.createUser(theManager);

		return userDetailsManager;
	}

}