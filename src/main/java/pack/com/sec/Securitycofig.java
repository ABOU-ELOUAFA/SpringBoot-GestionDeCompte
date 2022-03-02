package pack.com.sec;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.MessageDigestPasswordEncoder;

@Configuration
@EnableWebSecurity
public class Securitycofig extends WebSecurityConfigurerAdapter {
	@Autowired

	private DataSource dataSource;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		/*
		 * auth.inMemoryAuthentication().withUser("admin").password("{noop}2495").roles(
		 * "ADMIN", "USER");
		 * auth.inMemoryAuthentication().withUser("user").password("{noop}2495").roles(
		 * "USER");
		 */

		auth.jdbcAuthentication().dataSource(dataSource)
				.usersByUsernameQuery(
						"Select username as principal, password credentials,active from users where username=? ")
				.authoritiesByUsernameQuery(
						"select username as principal, roles as roles from users_roles users where username=?")
				.rolePrefix("ROLE_").passwordEncoder(new MessageDigestPasswordEncoder("MD5"));
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.formLogin().loginPage("/login");
		http.authorizeRequests().antMatchers("/index", "/consultercompte").hasAnyRole("USER");
		http.authorizeRequests().antMatchers("/saveoperation").hasAnyRole("ADMIN");
		http.exceptionHandling().accessDeniedPage("/403");

	}
}
