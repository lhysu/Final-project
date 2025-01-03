package com.project.zerowasteshop.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.zerowasteshop.donateitem.DonateItemVO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MemberService {
	
	@Autowired
	MemberMapper mapper;
	
	// 비밀번호 암호화하는 객체도 bean 으로 등록이 되어 있다.
	@Autowired
	private PasswordEncoder encoder;

	public MemberVO selectOne(String user_id) {
		return mapper.selectOne(user_id);
	}

	public int insertOK(MemberVO vo) {
		String encodedPwd = encoder.encode(vo.getPw());
		vo.setPw(encodedPwd);
		return mapper.insertOK(vo);
	}

	public int updateOK(MemberVO vo) {
		String encodedPwd = encoder.encode(vo.getPw());
		vo.setPw(encodedPwd);
		return mapper.updateOK(vo);
		
	}

	public int deleteOK(MemberVO vo) {
		return mapper.deleteOK(vo);
	}

	public MemberVO idCheck(String member_id) {
		return mapper.idCheck(member_id);
	}
	 

	public MemberVO selectId(MemberVO vo) {
		return mapper.selectId(vo);
	}


	public void deductPoints(String member_id, int points_used) {
		MemberVO vo = mapper.selectOne(member_id);
		int remainingPoints = vo.getPoints()-points_used;
		mapper.deductPoints(member_id,remainingPoints);
		
	}


	
	 public int updatePW(String member_id, String name, String email, String code)
	 { String temppw = encoder.encode(code); 
	 return mapper.updatePW(member_id,
	 name, email, temppw ); }

	public MemberVO emailCheck(String email) {
		return mapper.emailCheck(email);
	}

	public MemberVO memberCheck(MemberVO vo) {
		MemberVO vo2 = mapper.selectOne(vo.member_id);
		if(encoder.matches(vo.getPw(), vo2.getPw())) {
			return vo2;
		}else {
			return null;
		}
		
	}

	//상품 구매 후 포인트 적립
	public void addPoints(String member_id, int addPoints) {
		MemberVO vo = mapper.selectOne(member_id);
		addPoints = vo.getPoints()+ addPoints;
		mapper.addPoints(member_id,addPoints);	
	}

	public int getPointsByUserId(String user_id) {
		return mapper.getPointsByUserId(user_id);

	}



}
