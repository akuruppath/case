package com.afkl.travel.exercise.config;

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

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests() 
                .antMatchers("/, /**").permitAll()
                .antMatchers("/locations/**").hasRole("USER")
                .antMatchers("/actuator/**").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .httpBasic()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}

	@Bean
	@Override
	public UserDetailsService userDetailsService() {

		UserDetails theUser = User.withUsername("someuser")
				.passwordEncoder(PasswordEncoderFactories.createDelegatingPasswordEncoder()::encode).password("psw")
				.roles("USER").build();

		UserDetails theManager = User.withUsername("ops")
				.passwordEncoder(PasswordEncoderFactories.createDelegatingPasswordEncoder()::encode).password("psw")
				.roles("ADMIN").build();

		InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();

		userDetailsManager.createUser(theUser);
		userDetailsManager.createUser(theManager);

		return userDetailsManager;
	}

}