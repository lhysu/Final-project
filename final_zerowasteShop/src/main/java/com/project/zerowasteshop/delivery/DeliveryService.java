package com.project.zerowasteshop.delivery;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
public class DeliveryService {
	
	@Autowired
	DeliveryMapper mapper;
    
	
	public DeliveryVO selectOne(DeliveryVO vo) {
		return mapper.selectOne(vo);
	}

	public List<DeliveryVO> selectAll() {
		return mapper.selectAll();
	}

	public List<DeliveryVO> searchList(String searchKey, String searchWord) {
		if(searchKey.equals("delivery_num")){
			return mapper.searchListDelivery_num("%"+searchWord+"%");
		} else {
			return mapper.searchListTracking_num("%"+searchWord+"%");
		}
		
	}

	public int getTotalRows() {
		return mapper.getTotalRows();
	}
	
	public List<DeliveryVO> selectAllPageBlock(int cpage, int pageBlock) {
		
		int startRow = (cpage - 1) * pageBlock;
		log.info("startRow : {}", startRow);
		log.info("pageBlock : {}", pageBlock);
		
		return mapper.selectAllPageBlock(startRow, pageBlock);
	}
	
	public int getSearchTotalRows(String searchKey, String searchWord) {
		if(searchKey.equals("product_name")){
			return mapper.getSearchTotalRowsProduct_name("%"+searchWord+"%");
		} else {
			return mapper.getSearchTotalRowsTracking_num("%"+searchWord+"%");
		}
	}
	
	public List<DeliveryVO> searchListPageBlock(String searchKey, String searchWord, 
			int cpage, int pageBlock) {
		
		int startRow = (cpage - 1) * pageBlock + 1;
		int endRow = startRow + pageBlock - 1;
		log.info("startRow:{}", startRow);
		log.info("endRow:{}", endRow);

		if (searchKey.equals("product_name")) {
			return mapper.searchListPageBlockProduct_name("%"+searchWord+"%", startRow, endRow);
		} else {
			return mapper.searchListPageBlockTracking_num("%"+searchWord +"%", startRow, endRow);
		}	
	}
}
