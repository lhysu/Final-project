package com.project.zerowasteshop.donateitem;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Controller
public class DonateItemController {
	
	@Autowired
	DonateItemService service;
	
	@GetMapping({"/donateItem/d_selectAll"})
	public String d_selectAll(Model model,
			@RequestParam(defaultValue = "1")int cpage,
			@RequestParam(defaultValue = "10")int pageBlock) {
		log.info("/donateItem/d_selectAll");
		log.info("cpage:{}",cpage);
		log.info("pageBlock:{}",pageBlock);
		
//		List<DonateItemVO> list = service.selectAll();
		List<DonateItemVO> list = service.selectAllPageBlock(cpage,pageBlock);
		log.info("list.size():{}",list.size());
		
		//DB로부터 얻은 검색결과의 모든 행수
		int total_rows=service.getTotalRows();	
		log.info("total_rows:{}",total_rows);
		
		//int pageBlock=5;	//1개페이지에서 보여질 행수-파라미터로 받으면 된다.
		int totalPageCount=0;	
		
		//총 행카운트와 페이지블럭을 나눌 때의 알고리즘을 추가 
		if(total_rows%pageBlock!=0) {
			totalPageCount=total_rows/pageBlock+1;
		}else {
			totalPageCount=total_rows/pageBlock;
		}
		log.info("totalPageCount:{}",totalPageCount);
		
		
		model.addAttribute("totalPageCount",totalPageCount);
		
		model.addAttribute("list", list);
		return "community/donateItem/selectAll";
	}
	
	@GetMapping({"/donateItem/d_selectOne"})
	public String d_selectOne(Model model,DonateItemVO vo) {
		log.info("/donateItem/d_selectOne");
		log.info("vo:{}",vo);
		DonateItemVO vo2 = service.selectOne(vo);
		log.info("vo2:{}",vo2);
		
		model.addAttribute("vo2", vo2);
		return "community/donateItem/selectOne";
	}
	
	@GetMapping({"/donateItem/d_searchList"})
	public String d_searchList(Model model,
			@RequestParam(defaultValue = "member_id")String searchKey,
			@RequestParam(defaultValue = "us")String searchWord,
			@RequestParam(defaultValue = "1")int cpage,
			@RequestParam(defaultValue = "10")int pageBlock) {
		log.info("/donateItem/d_searchList");
		log.info("cpage:{}",cpage);
		log.info("pageBlock:{}",pageBlock);

//		List<DonateItemVO> list = service.searchList(searchKey,searchWord);
		List<DonateItemVO> list = service.searchListPageBlock(searchKey,searchWord,cpage,pageBlock);
		log.info("list.size():{}",list.size());
		
		//DB로부터 얻은 검색결과의 모든 행수
		int total_rows=service.getSearchTotalRows(searchKey,searchWord);	
		log.info("total_rows:{}",total_rows);
		
		//int pageBlock=5;	//1개페이지에서 보여질 행수-파라미터로 받으면 된다.
		int totalPageCount=0;	
		
		//총 행카운트와 페이지블럭을 나눌 때의 알고리즘을 추가 
		if(total_rows%pageBlock!=0) {
			totalPageCount=total_rows/pageBlock+1;
		}else {
			totalPageCount=total_rows/pageBlock;
		}
		log.info("totalPageCount:{}",totalPageCount);
		
		
		model.addAttribute("totalPageCount",totalPageCount);
		
		model.addAttribute("list", list);
		return "community/donateItem/selectAll";
	}
	
	@GetMapping({"/donateItem/d_updateOK"})
	public String d_updateOK(Model model,DonateItemVO vo) {
		log.info("/donateItem/d_updateOK");
		log.info("vo:{}",vo);
		int result = service.updateOK(vo);
		log.info("result:{}",result);
		
		return "redirect:/donateItem/d_selectOne?donateItem_num="+vo.getDonateItem_num();
	}
	
	@GetMapping({"/donateItem/d_deleteOK"})
	public String d_deleteOK(Model model,DonateItemVO vo) {
		log.info("/donateItem/d_deleteOK");
		log.info("vo:{}",vo);
		int result = service.deleteOK(vo);
		log.info("result:{}",result);
		
		if(result==1) {
			return "redirect:/donateItem/d_selectAll";			
		}else {
			return "redirect:/donateItem/d_selectOne?donateItem_num="+vo.getDonateItem_num();	
			
		}
		
	}
	
	@GetMapping({"/donateItem/d_insert"})
	public String d_insert() {
		log.info("/donateItem/d_insert");
		return "community/donateItem/insert";
		
	}
	
	@PostMapping({"/donateItem/d_insertOK"})
	public String d_insertOK(Model model,DonateItemVO vo) {
		log.info("/donateItem/d_insertOK");
		log.info("vo:{}",vo);
		
		int result = service.insertOK(vo);
		
		if(result==1) {
			return "redirect:/donateItem/d_selectAll";		
		}else {
			return "redirect:/donateItem/insert";		
			
		}

	}

}
