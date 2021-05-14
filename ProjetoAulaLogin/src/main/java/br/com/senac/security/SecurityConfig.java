package br.com.senac.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	private static String[] PUBLIC_MATCHERS = {"/h2-console/**", "/curso/index", "/password/**"};
	private static String[] PUBLIC_MATCHERS_GET = {"/categoria/**", "/password/**"};
	
	@Autowired
	private CurrentUserDetailService userDetailService;
	
	//metodo que define as configurações do spring security
	protected void configure(HttpSecurity http) throws Exception{
		
		// definindo as configurações de autorização
		http
			.authorizeRequests()
				// só serão liberadas as requisições GET dos mapeamentos que estão em PUBLIC_MATCHERS_GET
				.antMatchers(HttpMethod.GET, PUBLIC_MATCHERS_GET).permitAll()
				.antMatchers(HttpMethod.POST,PUBLIC_MATCHERS_GET).permitAll()
				
				// o que estive em PUBLIC_MATCHERS, pode liberar o acesso
				.antMatchers(PUBLIC_MATCHERS).permitAll()
				.antMatchers(PUBLIC_MATCHERS).hasRole("ADMIN")
				
				.antMatchers("/curso/formInserirCurso").permitAll()
				.antMatchers("/curso/formInserirCurso").hasRole("USER")
				.antMatchers("/curso/formInserirCurso").hasAnyAuthority("insert")
				//qualquer outra requisição é preciso autenticar
				.anyRequest().authenticated() 
				.and()
			.formLogin()
				.loginProcessingUrl("/signin")
				.loginPage("/login")
					.permitAll()
				.usernameParameter("txtUsername")
				.passwordParameter("txtPassword")
				.permitAll() // deixo todos acessarem meu form de login
				.and()
			.logout()
				// para sair será pelo /logout
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.logoutSuccessUrl("/login");
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception{
		web.ignoring().antMatchers("/css/**", "/js/**", "/vendor/**", "/fonts/**", "/images/**");
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(userDetailService).passwordEncoder(new BCryptPasswordEncoder());
	}
}
