package com.project.zerowasteshop.delivery;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.zerowasteshop.donateitem.DonateItemVO;
import com.project.zerowasteshop.recyclelife.RecycleLifeVO;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class DeliveryController {
	
	@Autowired
	DeliveryService service;
		
	
	@GetMapping("/delivery/selectOne")
	public String selectOne(@RequestParam("delivery_num") int delivery_num , DeliveryVO vo, Model model) {
		log.info("/delivery/selectOne");
		log.info("vo : {}", vo);
		
		DeliveryVO vo2 = service.selectOne(vo);
		log.info("vo2", vo2);
		
		model.addAttribute("vo2", vo2);
		
		if (vo2 == null) {
	        // 에러 처리 로직 또는 기본 객체 설정
	        model.addAttribute("error", "해당 배송 정보를 찾을 수 없습니다.");
	        return "errorPage"; // 오류 페이지로 이동하거나 기본 페이지 설정
	    }
		
		return "delivery/selectOne";
        
    }
		
	
	@GetMapping("/delivery/selectAll")
	public String selectAll(Model model, HttpSession session,
			@RequestParam(defaultValue = "1") int cpage,
            @RequestParam(defaultValue = "5") int pageBlock) {
		log.info("/delivery/selectAll");
		log.info("cpage : {}", cpage);
        log.info("pageBlock : {}", pageBlock);
		
        String user_id = (String) session.getAttribute("user_id"); // session에서 user_id 가져오기
        List<DeliveryVO> Dlist = service.selectAllByUserId(user_id); // user_id별 데이터 조회
        model.addAttribute("Dlist", Dlist);
        
		List<DeliveryVO> list = service.selectAllPageBlock(cpage, pageBlock);
		log.info("list.size() : [}", list.size());
		
		model.addAttribute("list", list);
		
		// 총 페이지 수 계산
        int total_rows = service.getTotalRows();
        log.info("total_rows:{}", total_rows);
        int totalPageCount = 0;

        if(total_rows%pageBlock!=0) {
			totalPageCount=total_rows/pageBlock+1;
		}else {
			totalPageCount=total_rows/pageBlock;
		}
        log.info("totalPageCount:{}", totalPageCount);

        model.addAttribute("totalPageCount", totalPageCount);
		
		return "delivery/selectAll";
	}
	
	@GetMapping("/delivery/searchList")
	public String searchList(Model model,
			@RequestParam(defaultValue = "delivery_num") String searchKey,
			@RequestParam(defaultValue = "1") String searchWord,
			@RequestParam(defaultValue = "1")int cpage,
			@RequestParam(defaultValue = "10")int pageBlock) {
		log.info("/delivery/searchList");
		log.info("searchWord : {}", searchWord);
		log.info("searchKey : {}", searchKey);
		log.info("cpage : {}",cpage);
		log.info("pageBlock : {}",pageBlock);
		
		//List<DeliveryVO> list = service.searchList(searchKey, searchWord);
		List<DeliveryVO> list = service.searchListPageBlock(searchKey,searchWord,cpage,pageBlock);
		log.info("list.size() : {}", list.size());
		
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
		
		return "delivery/selectAll";
	}
	

}
