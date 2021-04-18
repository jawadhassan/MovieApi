package com.movieapidemo.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.movieapidemo.service.ApiUserDetailService;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	ApiUserDetailService apiUserDetailService;
	
	@Bean
	public BCryptPasswordEncoder encoder() {
	return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.userDetailsService(apiUserDetailService).passwordEncoder(encoder());
//		auth
//			.jdbcAuthentication()
//			.dataSource(dataSource)
//			.passwordEncoder(encoder());
}
	
	@Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

	@Override
	protected void configure(HttpSecurity http) throws Exception {
//		http.csrf().disable()
//		 .authorizeRequests().antMatchers("/movie/check","/movie/print_json_sample").permitAll().
//		 anyRequest().authenticated();
		
		
		 http.csrf().disable();
         // dont authenticate this particular request
//         .authorizeRequests().antMatchers("/print_json_sample","/check").permitAll().
//         // all other requests need to be authenticated
//         anyRequest().authenticated();
	}
	
	
	
		
	
		
	
}
