package com.project.zerowasteshop.donateitem;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DonateItemService {

	@Autowired 
	DonateItemMapper mapper;

	public List<DonateItemVO> selectAll() {
		
		return mapper.selectAll();
	}

	public List<DonateItemVO> selectAllPageBlock(int cpage, int pageBlock) {
		//mysql인 경우 limit 시작행을 얻어내는 알고리즘 필요
		//예: 1페이지(0,pageBlock), 2페이지(5,pageBlock), 3페이지(10,pageBlock)
		int startRow=(cpage-1)*pageBlock;
		log.info("startRow:{}",startRow);
		log.info("pageBlock:{}",pageBlock);
		return mapper.selectAllPageBlock(startRow,pageBlock);
	}

	public int getTotalRows() {
		return mapper.getTotalRows();
	}

	public DonateItemVO selectOne(DonateItemVO vo) {
		return mapper.selectOne(vo);
	}

	public List<DonateItemVO> searchList(String searchKey, String searchWord) {
		if(searchKey.equals("member_id")) {
			return mapper.searchListId("%"+searchWord+"%");
		}else {
			return mapper.searchListItem("%"+searchWord+"%");
		}
		
		
	}

	public List<DonateItemVO> searchListPageBlock(String searchKey, String searchWord, int cpage, int pageBlock) {
		int startRow=(cpage-1)*pageBlock;	//mysql은 limit처리 시 0행부터 시작 (+1 안해도 됨)
		log.info("startRow:{}",startRow);
		log.info("pageBlock:{}",pageBlock);
		
		if(searchKey.equals("member_id")) {
			return mapper.searchListPageBlockId("%"+searchWord+"%",startRow,pageBlock);
		}else {
			return mapper.searchListPageBlockItem("%"+searchWord+"%",startRow,pageBlock);		
		}
	}

	public int getSearchTotalRows(String searchKey, String searchWord) {
		if(searchKey.equals("member_id")) {
			return mapper.getSearchTotalRowsId("%"+searchWord+"%");
		}else {
			return mapper.getSearchTotalRowsItem("%"+searchWord+"%");		
		}
	}

//	public int updateOK(DonateItemVO vo) {
//		return mapper.updateOK(vo);
//	}

	public int deleteOK(DonateItemVO vo) {
		return mapper.deleteOK(vo);
		
	}

	public int insertOK(DonateItemVO vo) {
		return mapper.insertOK(vo);
		
	}
}
