package com.project.zerowasteshop.member;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.project.zerowasteshop.donateitem.DonateItemVO;

@Mapper
public interface MemberMapper {

	public MemberVO selectOne(String member_id);

	public int insertOK(MemberVO vo);

	public int updateOK(MemberVO vo);

	public int deleteOK(MemberVO vo);

	public MemberVO idCheck(String member_id);

	public MemberVO login(MemberVO vo);

	public MemberVO selectId(MemberVO vo);

	public MemberVO selectPw(MemberVO vo);

	public void deductPoints(String member_id, int remainingPoints);


}
