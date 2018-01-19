package br.biblioteca.livros;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.context.annotation.Bean;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	/* Prof. precisou criar. Tinha que vir do Spring !!! */
	@Bean(name = BeanIds.AUTHENTICATION_MANAGER)
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

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
            .antMatchers(HttpMethod.GET, "/user/list").hasRole("BASIC")

            // ROLE - ADMIN
            .antMatchers(HttpMethod.GET, "/user/listadmin").hasRole("ADMIN")

            // PÁGINA DE LOGIN
            .and().formLogin().loginPage("/user/login").permitAll()

            // ACESSO NEGADO
            .and().exceptionHandling().accessDeniedPage("/teste")

            // LOGOUT
            .and().logout().permitAll();

// FALHA DE AUTENTICAÇÃO
/*
.failureHandler((req,res,exp)->{  // Failure handler invoked after authentication failure
String errMsg="";
if(exp.getClass().isAssignableFrom(BadCredentialsException.class)){
errMsg="Invalid username or password.";
}else{
errMsg="Unknown error - "+exp.getMessage();
}
req.getSession().setAttribute("message", errMsg);
res.sendRedirect("/login"); // Redirect user to login page with error message.
})
*/
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
	}
}

/*
.failureHandler((req,res,exp)->{  // Failure handler invoked after authentication failure
String errMsg="";
if(exp.getClass().isAssignableFrom(BadCredentialsException.class)){
errMsg="Invalid username or password.";
}else{
errMsg="Unknown error - "+exp.getMessage();
}
req.getSession().setAttribute("message", errMsg);
res.sendRedirect("/login"); // Redirect user to login page with error message.
})
*/