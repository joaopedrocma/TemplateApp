package br.com.keepcred.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebMvcSecurity
@ComponentScan(basePackages = { "br.com.keepcred" })
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource dataSource;

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests().antMatchers("/").permitAll().antMatchers("/keepcred/**").hasAnyRole("USER", "ADMIN")
				.antMatchers("/keepcred/admin/**").hasRole("ADMIN").anyRequest().authenticated().and().formLogin()
				.loginPage("/login").usernameParameter("username").passwordParameter("password").permitAll()
				.defaultSuccessUrl("/keepcred").and().logout().permitAll().and().exceptionHandling()
				.accessDeniedPage("/403").and().csrf().disable();
	}

	@Autowired
	public void configureAuthentication(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(dataSource)
				.usersByUsernameQuery("select username, password, enabled from users where username=? and enabled=1")
				.authoritiesByUsernameQuery(
						"select a.username, b.role from users a, user_roles b where a.username=? and a.user_role_id=b.user_role_id");
	}

	@Bean
	public PasswordEncoder passwordencoder() {
		return new BCryptPasswordEncoder();
	}

	// .passwordEncoder(passwordencoder())
	// .authoritiesByUsernameQuery("select a.username, b.role, c.authority from
	// users a, user_roles b, user_authorities c where a.username=? and
	// a.userid=b.userid and a.userid=c.userid");
}