package com.project.zerowasteshop.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MemberService {
	
	@Autowired
	MemberMapper mapper;
	
	public MemberVO selectOne(String user_id) {
		return mapper.selectOne(user_id);
	}


	public void deductPoints(String member_id, int points_used) {
		MemberVO vo = mapper.selectOne(member_id);
		int remainingPoints = vo.getPoints()-points_used;
		mapper.deductPoints(member_id,remainingPoints);
		
	}

	

}
