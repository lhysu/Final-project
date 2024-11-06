package com.project.zerowasteshop.member;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface MemberMapper {

	public MemberVO selectOne(String member_id);

	public void deductPoints(String member_id, int remainingPoints);



}
