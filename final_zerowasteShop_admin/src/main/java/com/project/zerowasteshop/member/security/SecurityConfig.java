package com.project.zerowasteshop.member.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration //설정 클래스라고 알려준다
@EnableWebSecurity //Security 를 설정하기 위한 어노테이션
public class SecurityConfig {
	
	@Bean //메소드에서 리턴되는 SecurityFilterChain 을 bean 으로 만들어준다.
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
		//화이트 리스트를 미리 배열에 넣어두기
		String[] whiteList= {"/member/required_login","/member/denied","/member/expired", "login/mailConfirm",
				"/member/login_fail","/member/login_success","/api/**","/admin/ad_login",
				"/img/**","/css/**","/js/**","/lib/**","/scss/**","/upload_img","/admin/adminJoin","/admin/adminInsert",
				"/error","/terms"
				};
		
		//메소드의 매개변수에 HttpSecurity 의 참조값이 전달되는데 해당 객체를 이용해서 설정을 한다음
		httpSecurity
		.headers(header->
			//동일한 origin 에서 iframe 을 사용할수 있도록 설정(default 값은 사용불가)
			header.frameOptions(option->option.sameOrigin()) //SmartEditor 에서 필요함
		)
		.csrf(csrf->csrf.disable())
		.authorizeHttpRequests(config -> 
			config
				.requestMatchers(whiteList).permitAll() //whiteList 요청은 로그인과 상관없이 모두 허용
				.requestMatchers("/admin/**").hasRole("ADMIN")// 접근제한
				.anyRequest().authenticated() //위에 명시한 이외의 모든 요청은 로그인해야지 요청가능하게
		)
		.formLogin(config -> 
			config
				//인증을 거치지 않은 사용자를 리다일렉트 시킬 경로 설정 
				.loginPage("/member/required_login")
				//로그인 처리를 할때 요청될 url 설정 ( spring security 가 login 처리를 대신 해준다)
				.loginProcessingUrl("/login")
				//로그인 처리를 대신 하려면 어떤 파라미터명으로 username 과 password 가 넘어오는지 알려주기 
				.usernameParameter("member_id") 
				.passwordParameter("pw")
				.successHandler(new AuthSuccessHandler())//로그인 성공 핸들러 등록
				.failureForwardUrl("/member/login_fail") //로그인 실패시 forward 될 url 설정
				.permitAll() //위에 명시한 모든 요청경로를 로그인 없이 요청할수 있도록 설정 
		)
		.logout(config ->
			config
				.logoutUrl("/logout")//Spring Security 가 자동으로 로그아웃 처리 해줄 경로 설정
				.logoutSuccessUrl("/admin/ad_login")//로그 아웃 이후에 리다일렉트 시킬 경로 설정
				.permitAll()
		)
		.exceptionHandling(config ->
			//403 forbidden 인 경우 forward 이동 시킬 경로 설정 
			config.accessDeniedPage("/member/denied")
		)
		.sessionManagement(config ->
			config
				.maximumSessions(1) //최대 허용 세션 갯수
				.expiredUrl("/member/expired") //허용 세션 갯수가 넘어서 로그인 해제된 경우 리다일렉트 이동시킬 경로
		);
		//설정된 정보대로 SecurityFilterChain 객체를 만들어서 반환한다 
		return httpSecurity.build();
	}
	
	//비밀번호를 암호화 해주는 객체를 bean 으로 만든다.
	@Bean
	PasswordEncoder passwordEncoder() { 
		return new BCryptPasswordEncoder();
	}
	//인증 메니저 객체를 bean 으로 만든다. (Spring Security 가 자동 로그인 처리할때도 사용되는 객체)
	@Bean
	AuthenticationManager authenticationManager(HttpSecurity http, 
			BCryptPasswordEncoder bCryptPasswordEncoder, UserDetailsService userDetailService) throws Exception {
	    
		return http.getSharedObject(AuthenticationManagerBuilder.class) 
	      .userDetailsService(userDetailService)
	      .passwordEncoder(bCryptPasswordEncoder)
	      .and()
	      .build();
	}
}












