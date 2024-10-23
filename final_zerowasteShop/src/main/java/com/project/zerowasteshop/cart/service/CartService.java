package com.project.zerowasteshop.cart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.zerowasteshop.cart.mapper.CartMapper;
import com.project.zerowasteshop.cart.model.CartVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CartService {

	@Autowired
	CartMapper mapper;

	public int insertOK(CartVO vo) {
		return mapper.insertOK(vo);
	}
	
	public int cartCheck(CartVO vo) {
		return mapper.cartCheck(vo);
	}
//
//	public List<CartVO> selectAll() {
//		return mapper.selectAll();
//	}

//	public CartVO selectOne(CartVO vo) {
//		return mapper.selectOne(vo);
//	}
//
//	public int updateOK(CartVO vo) {
//		return mapper.updateOK(vo);
//	}

	public int deleteOK(CartVO vo) {
		return mapper.deleteOK(vo);
	}

//	public List<CartVO> searchList(String searchKey, String searchWord) {
//		if (searchKey.equals("id")) {
//			return mapper.searchListId("%" + searchWord + "%");
//		} else {
//			return mapper.searchListName("%" + searchWord + "%");
//		}
//
//	}
//
	public int getTotalRows(String userID) {
		return mapper.getTotalRows(userID);
	}

	public int updateProductName() {
		return mapper.updateProductName();
	}
	
	public List<CartVO> selectAllPageBlock(int cpage, int pageBlock, String userID) {
		// MySql 인경우 limit 시작행을얻어내는 알고리즘이 필요하다.
		// 예:1페이지(0,pageBlock),2페이지(5,pageBlock),3페이지(10,pageBlock)
		int startRow = (cpage - 1) * pageBlock ;
		log.info("startRow:{}", startRow);
		log.info("pageBlock:{}", pageBlock);

		return mapper.selectAllPageBlock(startRow, pageBlock, userID);
	}

//	public int getSearchTotalRows(String searchKey, String searchWord) {
//		if (searchKey.equals("company")) {
//			return mapper.getSearchTotalRowsCompany("%" + searchWord + "%");
//		} else {
//			return mapper.getSearchTotalRowsreview_name("%" + searchWord + "%");
//		}
//	}
//
//	public List<CartVO> searchListPageBlock(String searchKey, String searchWord, 
//			int cpage, int pageBlock) {
//		// 오라클인경우 rownum으로 읽어 올 시작행과 끝행을 얻어내는 알고리즘이 필요하다.
//		// 예:1페이지(1-5),2페이지(6-10),3페이지(11-15)
//		int startRow = (cpage - 1) * pageBlock + 1;
//		int endRow = startRow + pageBlock - 1;
//		log.info("startRow:{}", startRow);
//		log.info("endRow:{}", endRow);
//
//		return mapper.searchListPageBlockreview_name("%" + searchWord + "%",startRow,endRow);
//	}
}
