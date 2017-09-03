package co.in.hawker.web.config;

import javax.servlet.Filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;
import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import co.in.hawker.config.AppConfig;

/**
 * @author Siva
 * 
 */
public class HawkerWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[] { AppConfig.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[] { WebMvcConfig.class, RepositoryRestMvcConfiguration.class };
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/hawker/rest/*" };
	}

	@Configuration
	@EnableWebSecurity
	@EnableGlobalMethodSecurity(prePostEnabled = true)
	protected static class SecurityConfig extends WebSecurityConfigurerAdapter {

		@Override
		@Autowired // <-- This is crucial otherwise Spring Boot creates its own
		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
			auth.inMemoryAuthentication().withUser("user").password("password").roles("USER").and().withUser("admin")
					.password("password").roles("USER", "ADMIN");
		}

		@Bean
		public PasswordEncoder passwordEncoder() {
			PasswordEncoder encoder = new BCryptPasswordEncoder();
			return encoder;
		}

		@Override
		protected void configure(HttpSecurity http) throws Exception {

			// TODO Enable for Form Login
			/*
			 * http.formLogin() .and()
			 * .httpBasic().disable().anonymous().disable().authorizeRequests().
			 * anyRequest().authenticated();
			 */

			http.csrf().disable();
		}
	}

	// Instead we used SecurityWebApplicationInitializer

	@Override
	protected Filter[] getServletFilters() {
		return new Filter[] {
				// new DelegatingFilterProxy("springSecurityFilterChain")
				new OpenEntityManagerInViewFilter() };
	}

}
