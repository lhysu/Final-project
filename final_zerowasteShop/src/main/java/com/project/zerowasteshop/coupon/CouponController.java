package com.project.zerowasteshop.coupon;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class CouponController {

	@Autowired
	CouponService service;
	
	@Autowired
	private HttpSession session;
		
	@GetMapping("/coupon/selectAll")
	public String cp_selectAll(Model model) {
		log.info("/coupon/selectAll");
		
		String user_id = (String) session.getAttribute("user_id"); // 세션에서 user_id 가져오기
		List<CouponVO> list = service.selectAll(user_id); 
		log.info("list.size():{}",list.size());
		
		model.addAttribute("list",list);
		model.addAttribute("user_id",user_id);
			
		return "coupon/selectAll"; //resources/templates폴더에서 찾는다.
	}	
	
}
