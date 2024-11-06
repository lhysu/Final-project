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

	public MemberVO selectId(MemberVO vo);

	public void deductPoints(String member_id, int remainingPoints);

	public int updatePW(String member_id, String name, String email, String temppw);

	public MemberVO findById(String member_id);

	public MemberVO emailCheck(String email);

	public int getPointsByUserId(String user_id);

}
