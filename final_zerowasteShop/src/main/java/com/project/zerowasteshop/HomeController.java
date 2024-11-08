package com.project.zerowasteshop;

import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.project.zerowasteshop.order.BestsellerService;
import com.project.zerowasteshop.product.NaverShopSearch;
import com.project.zerowasteshop.product.model.ProductVO;
import com.project.zerowasteshop.product.service.ProductService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Controller
public class HomeController {
	
	private final BestsellerService bestsellerService;
	private final NaverShopSearch naverShopSearch;
    private int check = 0;
    
    @Autowired
    ProductService service;
    
	@GetMapping({"/","/home"})//웰컴파일없이 제어가능(index.html무시)
	public String home(Model model) {
		log.info("/home");
		
		// 여러 검색어에서 상품을 가져오기
        List<ProductVO> itemDtos = naverShopSearch.search();
    
        check = service.getTotalRows();
        
        if (check < 1) {
        	// 데이터베이스에 저장
        	service.saveProducts(itemDtos);
        	log.info("추가");
        } else {
        	log.info("추가 안 함");
        }
        log.info("check:{}", check);
        
		// 저장된 상품 정보 가져오기
        List<ProductVO> itemDtos2 = service.getProducts();
    
        int totalRows = service.getTotalRows();
    
        log.info("Total rows in database: {}", totalRows);
        itemDtos2.sort(Comparator.comparingInt(ProductVO::getProduct_num).reversed());
    
        // 슬라이싱: 상위 8개 상품만 표시
        List<ProductVO> displayItems;
        if (itemDtos2.size() > 8) {
            displayItems = itemDtos2.subList(0, 8);
        } else {
            displayItems = itemDtos2;
        }
    
        // 디버깅 로그 추가
        for (ProductVO product : displayItems) {
            log.debug("Display Product - Num: {}, Name: {}", product.getProduct_num(), product.getProduct_name());
        }
    
        model.addAttribute("displayItems", displayItems);
        model.addAttribute("totalItems", itemDtos2.size());
        
        // 베스트셀러 데이터 가져오기
        List<ProductVO> bestsellers = bestsellerService.getBestsellers(8); // 판매량 높은 상위 8개 상품
        model.addAttribute("bestsellers", bestsellers);
        model.addAttribute("totalBestsellers", bestsellers.size());
    
        return "home";
	}
	
	@GetMapping("/user/myPage")
	public String myPage(Model model, HttpSession session) {
		log.info("/myPage");	
		
		String user_id = (String) session.getAttribute("user_id");
		log.info("user_id : {}", user_id);
		
		int points = service.getPointsByUserId(user_id);
        model.addAttribute("points", points);
		
		return "user/myPage";
	}
	
	@GetMapping("/user/login")
	public String login() {
		log.info("/login");
		return "user/login";
	}
	
}
