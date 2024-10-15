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
}
