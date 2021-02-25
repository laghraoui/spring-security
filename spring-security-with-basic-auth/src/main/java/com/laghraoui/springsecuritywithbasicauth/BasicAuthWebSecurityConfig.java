package com.laghraoui.springsecuritywithbasicauth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class BasicAuthWebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
	    auth
	      .inMemoryAuthentication()
	      .withUser("laghraoui").password(passwordEncoder().encode("test")).roles("ADMIN")
	      .and()
	      .withUser("blog").password(passwordEncoder().encode("123")).roles("USER");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	    http
	      .authorizeRequests()
	        .anyRequest().fullyAuthenticated()
	        .and()
	      .httpBasic();
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
