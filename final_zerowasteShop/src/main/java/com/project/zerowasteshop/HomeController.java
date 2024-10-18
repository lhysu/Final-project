package com.project.zerowasteshop;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.project.zerowasteshop.product.NaverShopSearch;
import com.project.zerowasteshop.product.model.ProductVO;
import com.project.zerowasteshop.product.service.ProductService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Controller
public class HomeController {
	
	private final NaverShopSearch naverShopSearch;
    private int check = 0;
    
    @Autowired
    ProductService service;
    
	@GetMapping({"/","/home"})//웰컴파일없이 제어가능(index.html무시)
	public String home(Model model) {
		log.info("/home");
	
		// ? 뒤에 오는 것을 쓰고 싶다면 @RequestParam
    	// 여러 검색어에서 상품을 가져오기
        List<ProductVO> itemDtos = naverShopSearch.search();
    
        if (check == 0) {
        	// 데이터베이스에 저장
        	service.saveProducts(itemDtos);
        	check = 1;
        }
        
        model.addAttribute("itemDtos", itemDtos);
		
		return "home";//resources/templates폴더에서 찾는다
	}
	
	@GetMapping("/user/myPage")
	public String myPage() {
		log.info("/myPage");
		return "user/myPage";
	}
	
	@GetMapping("/user/login")
	public String login() {
		log.info("/login");
		return "user/login";
	}
	
}
