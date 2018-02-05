package br.biblioteca;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsService userDetailsService;

    /* !!! */
	@Bean(name = BeanIds.AUTHENTICATION_MANAGER)
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
    /* !!! */

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

/////////////////////////////////

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
	        .authorizeRequests()
	            .antMatchers("/resources/**", "/registration").permitAll()
	            .anyRequest().authenticated()
	            .and()
	        .formLogin()
	            .loginPage("/login")
	            .permitAll()
	            .and()
	        .logout()
	            .permitAll();
    }

/////////////////////////////////

/*
	@Override
	protected void configure(HttpSecurity http) throws Exception {

	    http
	    // 'CROSS SITE REQUEST FORGERY'
	    .csrf().disable()

	    // GET
	    .authorizeRequests().antMatchers(HttpMethod.GET, "/user/registration").permitAll()

	    // POST
	    .antMatchers(HttpMethod.POST, "/user/registration").permitAll()

	    // ROLE - BASIC
	    .antMatchers(HttpMethod.GET, "/user/listar").hasRole("BASIC")

	    // ROLE - ADMIN
	    .antMatchers(HttpMethod.GET, "/user/*r").hasRole("ADMIN")

	    // PÁGINA DE LOGIN
	    .and().formLogin().loginPage("/user/login").permitAll()

	    // ACESSO NEGADO
	    .and().exceptionHandling().accessDeniedPage("/teste")

	    // LOGOUT
	    .and().logout().permitAll();
	}
*/

/////////////////////////////////

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }
}
