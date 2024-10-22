package com.project.zerowasteshop.delivery;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class DeliveryController {
	
	@Autowired
	DeliveryService service;
		
	
	@GetMapping("/delivery/d_selectOne")
	public String d_selectOne(@RequestParam("tracking_num") String trackingNum, DeliveryVO vo, Model model) {
		log.info("/delivery/d_selectOne");
		log.info("vo : {}", vo);
		
		DeliveryVO vo2 = service.selectOne(vo);
		log.info("vo2", vo2);
		
		model.addAttribute("vo2", vo2);
		
		if (vo2 == null) {
	        // 에러 처리 로직 또는 기본 객체 설정
	        model.addAttribute("error", "해당 배송 정보를 찾을 수 없습니다.");
	        return "errorPage"; // 오류 페이지로 이동하거나 기본 페이지 설정
	    }
		
		return "delivery/d_selectOne";
        
    }
		
	
	@GetMapping("/delivery/d_selectAll")
	public String d_selectAll(Model model) {
		log.info("/delivery/d_selectAll");
		
		List<DeliveryVO> list = service.selectAll();
		log.info("list.size() : [}", list.size());
		
		model.addAttribute("list", list);
		
		return "delivery/d_selectAll";
	}
	
	@GetMapping("/delivery/d_searchList")
	public String d_searchList(Model model,
			@RequestParam(defaultValue = "delivery_num") String searchKey,
			@RequestParam(defaultValue = "1") String searchWord) {
		log.info("/delivery/d_searchList");
		log.info("searchWord : {}", searchWord);
		log.info("searchKey : {}", searchKey);
		
		List<DeliveryVO> list = service.searchList(searchKey, searchWord);
		log.info("list.size() : {}", list.size());
		
		model.addAttribute("list", list);
		
		return "delivery/d_searchList";
	}
	

}
