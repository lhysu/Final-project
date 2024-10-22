package com.project.zerowasteshop.cart.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.project.zerowasteshop.cart.model.CartVO;

@Mapper
public interface CartMapper {

//	//추상메소드명(예:insertOK)이 sqlMapper_*.xml 문서의 id와 같아야한다.
	public int insertOK(CartVO vo);
//	
//	public List<CartVO> selectAll();

//	public CartVO selectOne(CartVO vo);
//
//	public int updateOK(CartVO vo);
	public int deleteOK(CartVO vo);

//	public List<CartVO> searchListId(String searchWord);
//
//	public List<CartVO> searchListName(String searchWord);
//
	public int getTotalRows(String userID);
//
	public int updateProductName();
	
	public List<CartVO> selectAllPageBlock(int startRow, int pageBlock, String userID);
//
//	public int getSearchTotalRowsCompany(String searchWord);
//
//	public int getSearchTotalRowsProduct_name(String searchWord);
//
//	public List<CartVO> searchListPageBlockCompany(String searchWord, int startRow, int endRow);
//
//	public List<CartVO> searchListPageBlockProduct_name(String searchWord, int startRow, int endRow);
}
