package com.project.zerowasteshop.recyclelifecomment;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RecycleLifeCommentMapper {

	RecycleLifeCommentVO selectOne(RecycleLifeCommentVO vo);

	List<RecycleLifeCommentVO> selectAll();

	int insertOK(RecycleLifeCommentVO vo);

	int deleteOK(RecycleLifeCommentVO vo);

	int updateOK(RecycleLifeCommentVO vo);

	List<RecycleLifeCommentVO> selectAllPageBlock(int startRow, int pageBlock);

	int getTotalRows();

}
