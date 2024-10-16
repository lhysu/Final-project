package com.project.zerowasteshop.product.controller;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.project.zerowasteshop.product.NaverShopSearch;
import com.project.zerowasteshop.product.model.ProductVO;
import com.project.zerowasteshop.product.service.ProductService;

import java.util.List;

@RequiredArgsConstructor // final 로 선언된 클래스를 자동으로 생성합니다.
@Controller
public class OnlineController {

    private final NaverShopSearch naverShopSearch;

    @Autowired
    ProductService service;
    
    @GetMapping("/admin/product/zerowasteProduct/list")
    public String getItems(Model model){
        // ? 뒤에 오는 것을 쓰고 싶다면 @RequestParam
        String resultString = naverShopSearch.search();

        List<ProductVO> itemDtos = naverShopSearch.fromJSONtoItems(resultString);
        
        // 데이터베이스에 저장
        service.saveProducts(itemDtos);
        
        model.addAttribute("itemDtos", itemDtos);

        return "admin/product/zerowasteProduct_list";
    }
}
