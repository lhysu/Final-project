package com.project.zerowasteshop.review.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.zerowasteshop.product.model.ProductVO;
import com.project.zerowasteshop.review.mapper.ReviewMapper;
import com.project.zerowasteshop.review.model.ReviewVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ReviewService {

	@Autowired
	ReviewMapper mapper;

//	public int insertOK(ReviewVO vo) {
//		return mapper.insertOK(vo);
//	}
//
//	public ReviewVO selectOne(ReviewVO vo) {
//		return mapper.selectOne(vo);
//	}
	
	public ReviewVO selectOneAdmin(ReviewVO vo) {
		return mapper.selectOneAdmin(vo);
	}

//	public int updateOK(ReviewVO vo) {
//		return mapper.updateOK(vo);
//	}
	
	public int updateOKAdmin(ReviewVO vo) {
		return mapper.updateOKAdmin(vo);
	}

//	public int deleteOK(ReviewVO vo) {
//		return mapper.deleteOK(vo);
//	}
	
	public int deleteOKAdmin(ReviewVO vo) {
		return mapper.deleteOKAdmin(vo);
	}

	public int getTotalRowsAdmin() {
		return mapper.getTotalRowsAdmin();
	}
	
//	public int getTotalRows(String userID) {
//		return mapper.getTotalRows(userID);
//	}

	public int updateProductName() {
		return mapper.updateProductName();
	}
	
	public List<ReviewVO> selectAllPageBlockAdmin(int cpage, int pageBlock) {
		// MySql 인경우 limit 시작행을얻어내는 알고리즘이 필요하다.
		// 예:1페이지(0,pageBlock),2페이지(5,pageBlock),3페이지(10,pageBlock)
		int startRow = (cpage - 1) * pageBlock ;
		log.info("startRow:{}", startRow);
		log.info("pageBlock:{}", pageBlock);

		return mapper.selectAllPageBlockAdmin(startRow, pageBlock);
	}
	
//	public List<ReviewVO> selectAllPageBlock(int cpage, int pageBlock, String userID) {
//		// MySql 인경우 limit 시작행을얻어내는 알고리즘이 필요하다.
//		// 예:1페이지(0,pageBlock),2페이지(5,pageBlock),3페이지(10,pageBlock)
//		int startRow = (cpage - 1) * pageBlock ;
//		log.info("startRow:{}", startRow);
//		log.info("pageBlock:{}", pageBlock);
//
//		return mapper.selectAllPageBlock(startRow, pageBlock, userID);
//	}
	

	public ProductVO selectProduct(ReviewVO vo) {
		return mapper.selectProduct(vo);
	}

	public int updateProductRating(int product_num, double rating) {
		return mapper.updateProductRating(product_num, rating);
	}
	
	public int updateProductRating2(int product_num) {
		return mapper.updateProductRating2(product_num);
	}


}
