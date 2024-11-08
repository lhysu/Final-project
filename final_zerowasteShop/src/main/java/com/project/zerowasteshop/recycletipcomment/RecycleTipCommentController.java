package com.project.zerowasteshop.recycletipcomment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller 
@RequestMapping("/community/recycleTipComment") // 패키지 및 URL 경로 변경
public class RecycleTipCommentController {
	
	@Autowired
	private RecycleTipCommentService service; // 서비스 클래스 변경

	// 댓글 등록 폼
	@GetMapping("/insert") 
	public String insert() {
		log.info("/community/recycleTipComment/insert 요청");
		return "community/recycleTipComment/insert"; 
	}
	
	// 댓글 삭제 폼
	@GetMapping("/delete") 
	public String delete() {
		log.info("/community/recycleTipComment/delete 요청");
		return "community/recycleTipComment/delete"; 
	}
	
	// 댓글 업데이트 폼
	@GetMapping("/update") 
	public String update(RecycleTipCommentVO vo, Model model) { // VO 클래스 변경
		log.info("/community/recycleTipComment/update 요청");
		log.info("vo : {}", vo);
		
		RecycleTipCommentVO fetchedComment = service.selectOne(vo); // 서비스 메서드 변경
		log.info("Fetched Comment: {}", fetchedComment);
		
		model.addAttribute("vo2", fetchedComment);
		return "community/recycleTipComment/update"; 
	}
	
//	// 모든 댓글 조회 (페이징)
//    @GetMapping("/selectAll")
//    public String selectAll(@RequestParam("recycleTip_num") int recycleTip_num,
//                            @RequestParam(defaultValue = "1") int cpage,
//                            @RequestParam(defaultValue = "5") int pageBlock,
//                            Model model) {
//        List<RecycleTipCommentVO> comments = service.selectAllPageBlock(recycleTip_num, cpage, pageBlock);
//        model.addAttribute("comments", comments);
//		
//		// 총 페이지 수 계산
//        int total_rows = service.getTotalRows();
//        log.info("total_rows:{}", total_rows);
//        int totalPageCount = 0;
//        
//        if (total_rows == 0) {
//            totalPageCount = 1;
//        } else {
//            totalPageCount = (int) Math.ceil((double) total_rows / pageBlock);
//        }
//        log.info("totalPageCount:{}", totalPageCount);
//        
//        model.addAttribute("totalPageCount", totalPageCount);
//        model.addAttribute("cpage", cpage);
//        model.addAttribute("pageBlock", pageBlock);
//		
//        return "comments/selectAll"; // 실제 뷰 이름으로 변경
//	}
	
	// 단일 댓글 조회
	@GetMapping("/selectOne") 
	public String selectOne(RecycleTipCommentVO vo, Model model) { // VO 클래스 변경
		log.info("/community/recycleTipComment/selectOne 요청");
		log.info("vo : {}", vo);
		
		RecycleTipCommentVO fetchedComment = service.selectOne(vo); 
		log.info("fetchedComment : {}", fetchedComment);
		
		model.addAttribute("vo2", fetchedComment);
		return "community/recycleTipComment/selectOne"; 
	}
	
	// 댓글 등록 처리
	@PostMapping("/insertOK") 
	public String insertOK(RecycleTipCommentVO vo, RedirectAttributes redirectAttributes) { // RedirectAttributes 추가
		log.info("/community/recycleTipComment/insertOK 요청");
		log.info("vo : {}", vo);
		
		int result = service.insertOK(vo); 
		log.info("result : {}", result);
		
		if(result == 1) { 
            redirectAttributes.addFlashAttribute("msg", "댓글이 성공적으로 등록되었습니다.");
            return "redirect:/community/recycleTip/selectOne?recycleTip_num=" + vo.getRecycleTip_num();
        } else { 
            redirectAttributes.addFlashAttribute("msg", "댓글 등록에 실패했습니다.");
            return "redirect:/community/recycleTip/selectOne?recycleTip_num=" + vo.getRecycleTip_num();
        } 
	}
	
	// 댓글 삭제 처리
	@PostMapping("/deleteOK") 
	public String deleteOK(RecycleTipCommentVO vo, RedirectAttributes redirectAttributes) { 
	    log.info("/community/recycleTipComment/deleteOK 요청");
		log.info("vo : {}", vo);
	    
	    int result = service.deleteOK(vo); 
	    log.info("result : {}", result);
	    
	    if(result == 1) { 
	        redirectAttributes.addFlashAttribute("msg", "댓글이 성공적으로 삭제되었습니다.");
	        return "redirect:/community/recycleTip/selectOne?recycleTip_num=" + vo.getRecycleTip_num();
	    } else { 
	        redirectAttributes.addFlashAttribute("msg", "댓글 삭제에 실패했습니다.");
	        return "redirect:/community/recycleTip/selectOne?recycleTip_num=" + vo.getRecycleTip_num();
	    } 
	}

	
	// 댓글 업데이트 처리
	@PostMapping("/updateOK") 
	public String updateOK(RecycleTipCommentVO vo, RedirectAttributes redirectAttributes) { // VO 클래스 변경 및 RedirectAttributes 추가
		log.info("/community/recycleTipComment/updateOK 요청");
		log.info("vo : {}", vo);
		
		int result = service.updateOK(vo); 
		log.info("result : {}", result);
		
		if(result == 1) { 
            redirectAttributes.addFlashAttribute("msg", "댓글이 성공적으로 수정되었습니다.");
            return "redirect:/community/recycleTip/selectOne?recycleTip_num=" + vo.getRecycleTip_num();
        } else { 
            redirectAttributes.addFlashAttribute("msg", "댓글 수정에 실패했습니다.");
            return "redirect:/community/recycleTip/selectOne?recycleTip_num=" + vo.getRecycleTip_num();
        } 
	}
	
	// 댓글 삭제 모달창 (동일한 메서드 유지)
	@RequestMapping(value="/deleteModal", method= {RequestMethod.GET, RequestMethod.POST})
	public void deleteModal() {
	    log.info("댓글 삭제 모달창");
	}
}
