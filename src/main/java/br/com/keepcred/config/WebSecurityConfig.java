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

	// private static final String[] CLASSPATH_RESOURCE_LOCATIONS = {
	// "classpath:/static/**", "classpath:/public/**" };

	@Autowired
	private DataSource dataSource;

	// @Override
	// protected void configure(HttpSecurity http) throws Exception {
	// http.authorizeRequests().antMatchers("/").permitAll().antMatchers("/user/**").hasRole("USER").antMatchers("/admin/**").hasRole("ADMIN")
	// .anyRequest().authenticated().and().formLogin().loginPage("/login").permitAll().defaultSuccessUrl("/user").and().logout()
	// .permitAll();
	// http.httpBasic();
	// }

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
				.usersByUsernameQuery("select username, password, enabled from users where username=?")
				.authoritiesByUsernameQuery(
						"select b.username, a.role from user_roles a, users b where b.username=? and a.userid=b.userid");
	}

	@Bean(name = "passwordEncoder")
	public PasswordEncoder passwordencoder() {
		return new BCryptPasswordEncoder();
	}

	// @Autowired
	// public void configureGlobal(AuthenticationManagerBuilder auth) throws
	// Exception {
	// auth.inMemoryAuthentication().withUser("Jotadev").password("adm").roles("ADMIN");
	// auth.inMemoryAuthentication().withUser("Weverson").password("cred.ufu").roles("USER");
	// auth.inMemoryAuthentication().withUser("Nayara").password("cred.ufu").roles("USER");
	// auth.inMemoryAuthentication().withUser("Fernando").password("cred.ufu").roles("USER");
	// auth.inMemoryAuthentication().withUser("Atendimento").password("credufu@2016").roles("USER");
	// }
}