package com.project.zerowasteshop.product.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.zerowasteshop.product.mapper.ProductMapper;
import com.project.zerowasteshop.product.model.ProductVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProductService {

	@Autowired
	ProductMapper mapper;
//	test
//	public int insertOK(ProductVO vo) {
//		return mapper.insertOK(vo);
//	}
//
//	public List<ProductVO> selectAll() {
//		return mapper.selectAll();
//	}
//
//	public ProductVO selectOne(ProductVO vo) {
//		return mapper.selectOne(vo);
//	}
//
//	public int updateOK(ProductVO vo) {
//		return mapper.updateOK(vo);
//	}
//
//	public int deleteOK(ProductVO vo) {
//		return mapper.deleteOK(vo);
//	}
//
//	public List<ProductVO> searchList(String searchKey, String searchWord) {
//		if (searchKey.equals("id")) {
//			return mapper.searchListId("%" + searchWord + "%");
//		} else {
//			return mapper.searchListName("%" + searchWord + "%");
//		}
//
//	}
//
	public int getTotalRows() {
		return mapper.getTotalRows();
	}

	public List<ProductVO> selectAllPageBlock(int cpage, int pageBlock) {
		// MySql 인경우 limit 시작행을얻어내는 알고리즘이 필요하다.
		// 예:1페이지(0,pageBlock),2페이지(5,pageBlock),3페이지(10,pageBlock)
		int startRow = (cpage - 1) * pageBlock ;
		log.info("startRow:{}", startRow);
		log.info("pageBlock:{}", pageBlock);

		return mapper.selectAllPageBlock(startRow, pageBlock);
	}

	public int getSearchTotalRows(String searchKey, String searchWord) {
		if (searchKey.equals("company")) {
			return mapper.getSearchTotalRowsCompany("%" + searchWord + "%");
		} else {
			return mapper.getSearchTotalRowsProduct_name("%" + searchWord + "%");
		}
	}

	public List<ProductVO> searchListPageBlock(String searchKey, String searchWord, 
			int cpage, int pageBlock) {
		// 오라클인경우 rownum으로 읽어 올 시작행과 끝행을 얻어내는 알고리즘이 필요하다.
		// 예:1페이지(1-5),2페이지(6-10),3페이지(11-15)
		int startRow = (cpage - 1) * pageBlock + 1;
		int endRow = startRow + pageBlock - 1;
		log.info("startRow:{}", startRow);
		log.info("endRow:{}", endRow);

		if (searchKey.equals("company")) {
			return mapper.searchListPageBlockCompany("%" + searchWord + "%",startRow,endRow);
		} else {
			return mapper.searchListPageBlockProduct_name("%" + searchWord + "%",startRow,endRow);
		}
	}
	
	// 상품 정보 저장
    public void saveProducts(List<ProductVO> products) {
        for (ProductVO product : products) {
            mapper.insertProduct(product); // 하나씩 저장
        }
    }

    // 저장된 상품 정보 가져오기
    public List<ProductVO> getProducts() {
        return mapper.getProducts();
    }

}
