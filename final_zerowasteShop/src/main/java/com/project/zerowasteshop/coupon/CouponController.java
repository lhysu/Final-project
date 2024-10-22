package com.project.zerowasteshop.coupon;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	
	@GetMapping("/admin/coupon/selectAll")
	public String ad_couponSelectAll(Model model,@RequestParam(defaultValue = "1")int cpage
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
	
	@GetMapping("/admin/coupon/searchList")
	public String ad_couponSearchList(Model model,
			@RequestParam(defaultValue = "member_id")String searchKey,
			@RequestParam(defaultValue = "9")String searchWord,
			@RequestParam(defaultValue = "1")int cpage
			,@RequestParam(defaultValue = "5")int pageBlock) {
		log.info("/member/searchList");
		log.info("searchKey:{}",searchKey);
		log.info("searchWord:{}",searchWord);
		log.info("cpage:{}",cpage);
		log.info("pageBlock:{}",pageBlock);
		
		List<CouponVO> list = service.searchListPageBlock(searchKey,searchWord,cpage,pageBlock);
		log.info("list.size():{}",list.size());
		
		model.addAttribute("list",list);
			
		int total_rows = service.getSearchTotalRows(searchKey,searchWord); //select count(*) total_rows from member;
		log.info("total_rows:{}",total_rows);
		//int pageBlock =5; //1개 페이지에서 보여질 행의 수. 파라미터로 받으면 됨.
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
    public String ad_uploadCoupons(@RequestParam("file") MultipartFile file, Model model) throws IOException {
        List<CouponVO> list = new ArrayList<>();

        // 엑셀 파일 처리
        try (Workbook workbook = new XSSFWorkbook(file.getInputStream())) {
            Sheet sheet = workbook.getSheetAt(0); // 첫 번째 시트를 가져옴

            for (Row row : sheet) {
                if (row.getRowNum() == 0) { // 헤더는 건너뜀
                    continue;
                }

                CouponVO coupon = new CouponVO();
                coupon.setCoupon_code(row.getCell(0).getStringCellValue());
                coupon.setCoupon_name(row.getCell(1).getStringCellValue());
                coupon.setDiscount_rate((int) row.getCell(2).getNumericCellValue());

                // member_id는 비어두기
                coupon.setMember_id(null);

                //날짜 필드
                coupon.setUse_sdate(row.getCell(3).getStringCellValue());
                coupon.setUse_edate(row.getCell(4).getStringCellValue());

                // 사용 여부
                if (row.getCell(5) != null) {
                    coupon.setUsed(row.getCell(5).getBooleanCellValue());
                } else {
                    coupon.setUsed(false); // 기본값으로 미사용 상태
                }

                list.add(coupon); // 리스트에 추가
            }

            // 쿠폰 리스트를 서비스에 넘겨서 저장
            service.createCoupons(list);
        }

        return "redirect:/admin/coupon/selectAll";
    }
		
	@GetMapping("/admin/coupon/update")
	public String ad_updateCoupon(CouponVO vo,Model model) {
		log.info("/admin/coupon/update");
		log.info("vo:{}",vo);
		
		CouponVO vo2 = service.selectOne(vo);
		log.info("vo2:{}",vo2);
		model.addAttribute("vo2",vo2);
		
		return "admin/coupon/update";
	}
	
}
