package com.project.zerowasteshop.review.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.project.zerowasteshop.product.model.ProductVO;
import com.project.zerowasteshop.review.model.ReviewVO;

@Mapper
public interface ReviewMapper {

//	//추상메소드명(예:insertOK)이 sqlMapper_*.xml 문서의 id와 같아야한다.
//	public int insertOK(ReviewVO vo);

	public ProductVO selectProduct(ReviewVO vo);
//
//	public ReviewVO selectOne(ReviewVO vo);
	public ReviewVO selectOneAdmin(ReviewVO vo);

//	public int updateOK(ReviewVO vo);
	public int updateOKAdmin(ReviewVO vo);

//	public int deleteOK(ReviewVO vo);
	public int deleteOKAdmin(ReviewVO vo);

	public int getTotalRowsAdmin();
	
//	public int getTotalRows(String userID);

	public int updateProductName();
	
	public List<ReviewVO> selectAllPageBlockAdmin(int startRow, int pageBlock);
	
//	public List<ReviewVO> selectAllPageBlock(int startRow, int pageBlock, String userID);

	public int updateProductRating(int product_num, double rating);
	public int updateProductRating2(int product_num);
}
