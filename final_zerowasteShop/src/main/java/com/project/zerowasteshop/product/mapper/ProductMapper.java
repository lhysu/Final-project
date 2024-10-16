package com.project.zerowasteshop.product.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.project.zerowasteshop.product.model.ProductVO;

@Mapper
public interface ProductMapper {

//	//추상메소드명(예:insertOK)이 sqlMapper_*.xml 문서의 id와 같아야한다.
//	public int insertOK(CartVO vo);
//	
//	public List<CartVO> selectAll();
//
//	public CartVO selectOne(CartVO vo);
//
//	public int updateOK(CartVO vo);
//	public int deleteOK(CartVO vo);
//
//	public List<CartVO> searchListId(String searchWord);
//
//	public List<CartVO> searchListName(String searchWord);
//
//	public int getTotalRows();
//
//	public List<CartVO> selectAllPageBlock(int startRow, int pageBlock);
//
//	public int getSearchTotalRowsId(String searchWord);
//
//	public int getSearchTotalRowsName(String searchWord);
//
//	public List<CartVO> searchListPageBlockId(String searchWord, int startRow, int endRow);
//
//	public List<CartVO> searchListPageBlockName(String searchWord, int startRow, int endRow);
	
	// 상품 정보 저장
    void insertProduct(ProductVO product);
    
    // 모든 상품 정보 가져오기
    List<ProductVO> getProducts();

}
