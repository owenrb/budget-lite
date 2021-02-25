package io.owenrbee.budget.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Value("${security.enabled:true}")
	private boolean enabled;

    @Override
    public void configure(WebSecurity web) throws Exception {

    	if(!enabled) {
    		web.ignoring().antMatchers("/**");
    	}
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	
    	if(!enabled) {
    		http.authorizeRequests().antMatchers("/**").permitAll();
    		return;
    	}
    	
    	// @formatter:off
        http.logout(l -> l
                .logoutSuccessUrl("/").permitAll()
             )
        	.csrf(c -> c
                     .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
            	        )
            .authorizeRequests(a -> a
                .antMatchers("/", "/error", "/webjars/**").permitAll()
                .anyRequest().authenticated()
            )
            .exceptionHandling(e -> e
                .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
            )
            .oauth2Login()
            ;
        // @formatter:on
    }

}
