
package com.sabre.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 *
 * @author Andrzej
 */

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
        private RESTAuthenticationEntryPoint authenticationEntryPoint;
        private RESTAuthenticationFailureHandler authenticationFailureHandler;
        private RESTAuthenticationSuccessHandler authenticationSuccessHandler;
        
        @Autowired
        public SecurityConfig(RESTAuthenticationEntryPoint authenticationEntryPoint, RESTAuthenticationFailureHandler authenticationFailureHandler,
                              RESTAuthenticationSuccessHandler authenticationSuccessHandler) {
            this.authenticationEntryPoint = authenticationEntryPoint;
            this.authenticationFailureHandler = authenticationFailureHandler;
            this.authenticationSuccessHandler = authenticationSuccessHandler;
        }
        

        
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
            .csrf().disable()
            .authorizeRequests()
                .anyRequest().permitAll()
            .and()
                .exceptionHandling()
                .authenticationEntryPoint(authenticationEntryPoint)
            .and()
                .formLogin()
                .successHandler(authenticationSuccessHandler)
                .failureHandler(authenticationFailureHandler);
            
	}
}
