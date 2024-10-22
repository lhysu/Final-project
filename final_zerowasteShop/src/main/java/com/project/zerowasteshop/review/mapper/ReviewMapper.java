package com.project.zerowasteshop.review.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.project.zerowasteshop.review.model.ReviewVO;

@Mapper
public interface ReviewMapper {

//	//추상메소드명(예:insertOK)이 sqlMapper_*.xml 문서의 id와 같아야한다.
	public int insertOK(ReviewVO vo);
//	
//	public List<CartVO> selectAll();

	public ReviewVO selectOne(ReviewVO vo);

	public int updateOK(ReviewVO vo);
	public int deleteOK(ReviewVO vo);

//	public List<CartVO> searchListId(String searchWord);
//
//	public List<CartVO> searchListName(String searchWord);
//
	public int getTotalRows(String userID);
//
	public int updateProductName();
	
	public List<ReviewVO> selectAllPageBlock(int startRow, int pageBlock, String userID);
//
//	public int getSearchTotalRowsCompany(String searchWord);
//
//	public int getSearchTotalRowsProduct_name(String searchWord);
//
//	public List<ReviewVO> searchListPageBlockCompany(String searchWord, int startRow, int endRow);
//
//	public List<ReviewVO> searchListPageBlockProduct_name(String searchWord, int startRow, int endRow);
}
