package com.project.zerowasteshop.likeList.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.project.zerowasteshop.likeList.model.LikeListVO;

@Mapper
public interface LikeListMapper {

//	//추상메소드명(예:insertOK)이 sqlMapper_*.xml 문서의 id와 같아야한다.
	public int insertOK(LikeListVO vo);
	
	public int likeListCheck(LikeListVO vo);
//	
//	public List<LikeVO> selectAll();

//	public LikeVO selectOne(LikeVO vo);
//
//	public int updateOK(LikeVO vo);
	public int deleteOK(LikeListVO vo);

//	public List<LikeVO> searchListId(String searchWord);
//
//	public List<LikeVO> searchListName(String searchWord);
//
	public int getTotalRows(String userID);
//
//	public int updateProductName();
	
	public List<LikeListVO> selectAllPageBlock(int startRow, int pageBlock, String userID);
//
//	public int getSearchTotalRowsCompany(String searchWord);
//
//	public int getSearchTotalRowsProduct_name(String searchWord);
//
//	public List<LikeVO> searchListPageBlockCompany(String searchWord, int startRow, int endRow);
//
//	public List<LikeVO> searchListPageBlockProduct_name(String searchWord, int startRow, int endRow);
}
