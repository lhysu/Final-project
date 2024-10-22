package com.project.zerowasteshop.recyclelife;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RecycleLifeMapper {

	public List<RecycleLifeVO> selectAll();

	public RecycleLifeVO selectOne(RecycleLifeVO vo);

	public int insertOK(RecycleLifeVO vo);

	public int updateOK(RecycleLifeVO vo);

	public int deleteOK(RecycleLifeVO vo);

	public List<RecycleLifeVO> searchList(String string);

	public List<RecycleLifeVO> selectAllPageBlock(int startRow, int pageBlock);

	public int getTotalRows();

}
