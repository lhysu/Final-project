package com.project.zerowasteshop.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.zerowasteshop.donateitem.DonateItemVO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MemberService {
	
	@Autowired
	MemberMapper mapper;
	

	public MemberVO selectOne(MemberVO vo) {
		return mapper.selectOne(vo);
	}

	public int insertOK(MemberVO vo) {
		return mapper.insertOK(vo);
	}

	public int updateOK(MemberVO vo) {
		return mapper.updateOK(vo);
		
	}

	public int deleteOK(MemberVO vo) {
		return mapper.deleteOK(vo);
	}

	public MemberVO idCheck(String member_id) {
		return mapper.idCheck(member_id);
	}

	public MemberVO login(MemberVO vo) {
		return mapper.login(vo);
	}

	public MemberVO selectId(MemberVO vo) {
		return mapper.selectId(vo);
	}

	public MemberVO selectPw(MemberVO vo) {
		return mapper.selectPw(vo);
	}

	

}
