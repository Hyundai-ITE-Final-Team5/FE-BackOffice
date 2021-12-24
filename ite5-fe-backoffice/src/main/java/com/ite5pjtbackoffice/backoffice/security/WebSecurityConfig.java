package com.ite5pjtbackoffice.backoffice.security;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import lombok.extern.slf4j.Slf4j;

@EnableWebSecurity
@Slf4j
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Resource
	private CustomUserDetailsService customUserDetailsService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {	
		//로그인 방식 설정
		http.formLogin() 								//FormLoginConfiguer configuer = ~~ 이렇게 받아서 잘 쓰지는 않고 바로쓴다.
		.loginPage("/loginform")			 			//요청 경로이므로 @requestmapping 메서드를 만들어야한다. //default: /login(GET)
		.usernameParameter("mid")					//default: username //설정안하면
		.passwordParameter("mpassword")				//default: password //설정안하면
		.loginProcessingUrl("/login")   			//default: /login(POST) post방식으로 로그인을 요청해야한다.
		.defaultSuccessUrl("/admin")
		.failureUrl("/loginError"); 		//default: /login?error
		
		//로그아웃 설정
		http.logout()
			.logoutUrl("/logout") 					//default: /logout
			.logoutSuccessUrl("/loginform");
		
		//사이트간 요청 위조 방지 비활성화
		http.csrf().disable();
		
		//요청 경로 권한 설정
		http.authorizeRequests()
			//아래 board의 요청은 인증이 되어야한다.
			.antMatchers("/admin/**").hasAuthority("ROLE_ADMIN")
			.antMatchers("/**").permitAll();
		
		//권한 없음(403)일 경우 이동할 경로 설정
		http.exceptionHandling().accessDeniedPage("/accessDenied");
	
		//CSRF 비활성화
		http.csrf().disable();
		
		//CORS 설정 활성화
		http.cors();
		
	}	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		log.info("configure(AuthenticationManagerBuilder auth) 실행");
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(customUserDetailsService);
		provider.setPasswordEncoder(passwordEncoder());
		auth.authenticationProvider(provider);
	}	
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		log.info("configure(WebSecurity web) 실행");
		DefaultWebSecurityExpressionHandler defaultWebSecurityExpressionHandler = new DefaultWebSecurityExpressionHandler();
		defaultWebSecurityExpressionHandler.setRoleHierarchy(roleHierarchyImpl());	
		web.expressionHandler(defaultWebSecurityExpressionHandler);
		web.ignoring()
		.antMatchers("/images/**")
		.antMatchers("/css/**")
		.antMatchers("/js/**")
		.antMatchers("/bootstrap-4.6.0-dist/**")
		.antMatchers("/jquery/**")
		.antMatchers("/favicon.ico");		
	}	
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		//return new BCryptPasswordEncoder();
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
	
	@Bean
	public RoleHierarchyImpl roleHierarchyImpl() {
		log.info("실행");
		RoleHierarchyImpl roleHierarchyImpl = new RoleHierarchyImpl();
		roleHierarchyImpl.setHierarchy("ROLE_ADMIN > ROLE_MANAGER > ROLE_USER");
		return roleHierarchyImpl;
	}
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	//springsecurity가 실행되면 이 객체를 찾을 것이다.
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration conf = new CorsConfiguration();
		//모든 요청 사이트 허용
		conf.addAllowedOrigin("*");
		//모든 요청 방식 허용
		conf.addAllowedMethod("*");
		//모든 요청 헤드 허용
		conf.addAllowedHeader("*");//"Authorization"라고 넣어도 된다. //"*"는 모든 header를 허용하겠다는 것이다.
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", conf);
		return source;
		
	}
}
 
 