package org.glytoucan.api.security;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Bean
	AuthenticationProvider authenticationProvider() {
		return new UserAuthenticationProvider();
	}

    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	http.csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
    	.and().authenticationProvider(authenticationProvider())
                .authorizeRequests()
                .antMatchers(GET, "/").permitAll()
                .antMatchers(GET, "/swagger-ui.html").permitAll()
                .antMatchers(GET, "/webjars/springfox-swagger-ui/**").permitAll()
                .antMatchers(GET, "/v2/api-docs/**").permitAll()
                .antMatchers(GET, "/configuration/**").permitAll()
                .antMatchers(GET, "/swagger-resources/**").permitAll()
                .antMatchers(POST, "/glycans/**").hasRole("USER")
                .antMatchers(GET, "/glycans/**").hasRole("USER")
                .antMatchers(POST, "/Registries/**").hasAuthority("ROLE_USER")
                .anyRequest().authenticated()
    			.and().httpBasic()
    			.and().logout()
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
    }
}
