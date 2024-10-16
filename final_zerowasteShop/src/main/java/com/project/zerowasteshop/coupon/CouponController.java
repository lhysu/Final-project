package com.project.zerowasteshop.coupon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.ss.usermodel.*;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class CouponController {

	@Autowired
	CouponService service;
		
	@GetMapping("/coupon/selectAll")
	public String cp_selectAll(Model model) {
		log.info("/coupon/selectAll");
		
		List<CouponVO> list = service.selectAll(); 
		log.info("list.size():{}",list.size());
		
		model.addAttribute("list",list);
			
		return "coupon/selectAll"; //resources/templates폴더에서 찾는다.
	}
	
	@GetMapping("/admin/coupon/selectAll")
	public String ad_cpSelectAll(Model model,@RequestParam(defaultValue = "1")int cpage
			,@RequestParam(defaultValue = "10")int pageBlock) {
		log.info("/admin/coupon/selectAll");
		log.info("cpage:{}",cpage);
		log.info("pageBlock:{}",pageBlock);
		
		List<CouponVO> list = service.selectAllPageBlock(cpage,pageBlock); //해당 페이지에 보여줄 5개행씩 만 검색
		log.info("list.size():{}",list.size());
		
		model.addAttribute("list",list);
		
		//DB로부터 얻은 검색결과의 모든 행의 수
		int total_rows = service.getTotalRows();
		log.info("total_rows:{}",total_rows);
		
		int totalPageCount; 
		
		//총 행카운트와 페이지블럭을 나눌 때의 알고리즘을 추가하기.
		if(total_rows%pageBlock!=0) {
			totalPageCount = (total_rows/pageBlock) +1;
		} else {
			totalPageCount = total_rows/pageBlock;
		}
		
		model.addAttribute("totalPageCount",totalPageCount);
		
		return "admin/coupon/selectAll";
	}
	
	@GetMapping("/admin/coupon/create")
	public String ad_createCoupon() {
		log.info("/admin/coupon/create");

		return "admin/coupon/create";
	}
	
	@PostMapping("/admin/coupon/upload")
	public String uploadCoupons(@RequestParam("file")MultipartFile file,Model model) throws IOException {
		
		List<CouponVO> list = new ArrayList<>();
		
		// DateTimeFormatter 설정 (날짜 형식이 "yyyy-MM-dd"인 경우)
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		
//		if (file.getOriginalFilename().endsWith(".xls") || file.getOriginalFilename().endsWith(".xlsx")) {
//		    //Workbook workbook = new XSSFWorkbook(file.getInputStream());
//		    Sheet sheet = workbook.getSheetAt(0);
//		    
//		    for (Row row : sheet) {
//		        CouponVO coupon = new CouponVO();
//		        coupon.setCoupon_code(row.getCell(0).getStringCellValue());
//		        coupon.setCoupon_name(row.getCell(1).getStringCellValue());
//		        coupon.setDiscount_rate((int) row.getCell(2).getNumericCellValue());
//		        coupon.setUse_sdate(Timestamp.valueOf(LocalDate.parse(row.getCell(3).getStringCellValue(), formatter).atStartOfDay()));
//		        coupon.setUse_edate(Timestamp.valueOf(LocalDate.parse(row.getCell(4).getStringCellValue(), formatter).atStartOfDay()));
//		        list.add(coupon);
//		    }
//		    
//		    service.createCoupons(list);
//		    workbook.close();
//		}
		
		return "redirect:/admin/coupon/selectAll";
	}
}
