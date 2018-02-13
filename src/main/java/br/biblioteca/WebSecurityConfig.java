package br.biblioteca;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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

    @Override
    protected void configure(HttpSecurity httpSec) throws Exception {

/* !!!
		httpSec.authorizeRequests().anyRequest().permitAll();
*/
    	httpSec.authorizeRequests()

		// Qualquer um pode
		.antMatchers(

		// Arquivos do site
		"/css/**",
		"/resources/**",
		
		// ------------ PÁGINAS ------------

		// Index
		"/index",

		// Livros
		"/livros/listar", "/livros/novo",

		// Autores
		"/autores/listar", "/autores/novo",

		// Empréstimos
		"/emprestimos/listar", "/emprestimos/novo",

		// Usuários
		"/user/listar",
		
		// Cadastrar-se como usuário
		"/registration",
		
		// Autenticar-se como usuário
		"/authentication",

		// !!! Teste
		"/teste"
		
		).permitAll()

		// Tem que estar autenticado
    	.anyRequest().authenticated()
    
    	// Login
		.and().formLogin()
			.loginPage("/login")
			.failureUrl("/login-erro")
			.permitAll()

    	// Logout
		.and()
			.logout()
			.logoutSuccessUrl("/logout")
			.permitAll();
	}

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }
}
