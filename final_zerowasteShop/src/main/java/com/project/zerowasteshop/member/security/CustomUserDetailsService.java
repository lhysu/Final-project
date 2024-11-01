package com.project.zerowasteshop.member.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.project.zerowasteshop.member.MemberMapper;
import com.project.zerowasteshop.member.MemberVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service //bean 으로 만들기 위해
public class CustomUserDetailsService implements UserDetailsService{
	
	@Autowired
	MemberMapper mapper;
	
	//Spring Security 가 로그인 처리시 호출하는 메소드 
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//1. form 에 입력한 userName 을 이용해서 사용자의 자세한 정보를 얻어온다.
		MemberVO vo=mapper.findById(username);
		//만일 저장된 userName 이 없다면 
		if(vo==null) {
			//예외를 발생시킨다
			throw new UsernameNotFoundException("존재하지 않는 사용자 입니다");
		}
		//2. UserDetails 객체에 해당정보를 담아서 리턴해 주어야 한다
		//권한은 1개 이지만 List 에 담아서 
		List<GrantedAuthority> authList=new ArrayList<>();
		//Authority 는 role 앞에  "ROLE_" 를 붙여주여야 한다.
		authList.add(new SimpleGrantedAuthority("ROLE_USER"));
		for (GrantedAuthority authority : authList) {
			log.info("authority.getAuthority():{}",authority.getAuthority());
		}
		//Spring Security 가 제공하는 User 클래스는 UserDetails 인터페이스를 구현한 클래스 이다. 
		UserDetails ud=new User(vo.getMember_id(), vo.getPw(), authList);
		
		return ud;
	}

}









