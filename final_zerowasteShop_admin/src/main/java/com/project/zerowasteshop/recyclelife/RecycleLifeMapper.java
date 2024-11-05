
package com.project.zerowasteshop.recyclelife;

import java.util.List;

import org.apache.ibatis.annotations.Mapper; 
import org.apache.ibatis.annotations.Param;


@Mapper public interface RecycleLifeMapper {

	public List<RecycleLifeVO> selectAll();
	
	public RecycleLifeVO selectOne(RecycleLifeVO vo);
	
	public int insertOK(RecycleLifeVO vo);
	
	public int updateOK(RecycleLifeVO vo);
	
	public int deleteOK(RecycleLifeVO vo);
	
	public List<RecycleLifeVO> searchList(String string);
	
	public List<RecycleLifeVO> selectAllPageBlock(int startRow, int pageBlock);
	
	public int getTotalRows();
	
	public void increaseViews(int recycleLife_num);
	
	public List<RecycleLifeVO> selectTopViews();
	
	public void decreaseLikeCount(@Param("recycleLife_num") int recycleLife_num);
	
	public void increaseLikeCount(@Param("recycleLife_num") int recycleLife_num);
	
	public int getLikeCount(@Param("recycleLife_num") int recycleLife_num);

	public RecycleLifeVO getPost(int recycleLife_num);


}
