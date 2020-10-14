package com.luv2code.springsecurity.demo.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class DemoSecurityConfig extends WebSecurityConfigurerAdapter {

	//add a reference to our security data source
	
	@Autowired
	public DataSource securityDataSource;
	
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		//use jdbc authentication
		auth.jdbcAuthentication().dataSource(securityDataSource);
		
		System.out.println(":::::::::::::Security data source " + securityDataSource.toString());
		
		System.out.println(securityDataSource.getConnection().toString());
		
		System.out.println(auth.jdbcAuthentication().dataSource(securityDataSource));
		
		
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		
		System.out.println("Entered configure method from DemoSecurityConfig.java");
		
		http.authorizeRequests()
		.antMatchers("/").hasRole("EMPLOYEE")
		.antMatchers("/leaders/**").hasRole("MANAGER")
		.antMatchers("/systems/**").hasRole("ADMIN")
		.anyRequest().authenticated()
		.and()
		.formLogin()
		.loginPage("/showMyLoginPage")
		.loginProcessingUrl("/authenticateTheUser")
		.permitAll()
		.and()
		.logout().permitAll()
		.and()
		.exceptionHandling()
			.accessDeniedPage("/access-denied");
	}
}
