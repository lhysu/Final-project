package com.project.zerowasteshop.recyclelifecomment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Controller; 
import org.springframework.ui.Model; 
import org.springframework.web.bind.annotation.GetMapping; 
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller 
public class RecycleLifeCommentController {
	
	@Autowired
	RecycleLifeCommentService service;

	@GetMapping("/community/recycleLifeComment/insert") 
	public String insert() {
	log.info("/community/recycleLifeComment/insert"); 
	
	return "community/recycleLifeComment/insert"; 
	}
	
	@GetMapping("/community/recycleLifeComment/delete") 
	public String delete() {
	log.info("/community/recycleLifeComment/delete"); 
	
	return "community/recycleLifeComment/delete"; 
	}
	
	
	@GetMapping("/community/recycleLifeComment/update") 
	public String update(RecycleLifeCommentVO vo, Model model) {
	log.info("/community/recycleLifeComment/update"); 
	log.info("vo : {}", vo);
	 
	RecycleLifeCommentVO vo2 = service.selectOne(vo);
	 
	model.addAttribute("vo2", vo2);
	 
	return "community/recycleLifeComment/update"; 
	}
	
	
	@GetMapping("/community/recycleLifeComment/selectAll") 
	public String selectAll(Model model,
	@RequestParam(defaultValue = "1") int cpage,
	@RequestParam(defaultValue = "5") int pageBlock) {
	log.info("/community/recycleLifeComment/selectAll"); 
	log.info("cpage : {}", cpage); 
	log.info("pageBlock : {}", pageBlock);
	
	// List<RecycleLifeCommentVO> list = service.selectAll();
	List<RecycleLifeCommentVO> list = service.selectAllPageBlock(cpage, pageBlock);
	
	log.info("list.size() : {}", list.size()); 
	model.addAttribute(list);
	
	// 총 페이지 수 계산
    int total_rows = service.getTotalRows();
    log.info("total_rows:{}", total_rows);
    int totalPageCount = 0;

    if (total_rows / pageBlock == 0) {
        totalPageCount = 1;
    } else if (total_rows % pageBlock == 0) {
        totalPageCount = total_rows / pageBlock;
    } else {
        totalPageCount = total_rows / pageBlock + 1;
    }
    log.info("totalPageCount:{}", totalPageCount);

    model.addAttribute("totalPageCount", totalPageCount);
	
	return "community/recycleLifeComment/selectAll"; 
	}
	
	@GetMapping("/community/recycleLifeComment/selectOne") 
	public String selectOne(RecycleLifeCommentVO vo, Model model) {
	log.info("/community/recycleLifeComment/selectOne"); 
	log.info("vo : {}", vo);
	
	
	RecycleLifeCommentVO vo2 = service.selectOne(vo); 
	log.info("vo2 : {}", vo2);
	
	model.addAttribute("vo2", vo2);
	
	return "community/recycleLifeComment/selectOne"; 
	}
	
	@PostMapping("/community/recycleLifeComment/insertOK") 
	public String insertOK(RecycleLifeCommentVO vo) {
	log.info("/community/recycleLifeComment/insertOK"); 
	log.info("vo : {}", vo);
	
	int result = service.insertOK(vo); 
	log.info("result : {}", result);
	
	if(result == 1) { 
		return "redirect:/community/recycleLifeComment/selectAll";
	} else { 
		return "redirect:/community/recycleLife/selectOne"; 
		} 
	}
	
	@PostMapping("/community/recycleLifeComment/deleteOK") 
	public String deleteOK(RecycleLifeCommentVO vo) {
	log.info("/community/recycleLifeComment/deleteOK"); 
	log.info("vo : {}", vo);
	
	int result = service.deleteOK(vo); 
	log.info("result : {}", result);
	
	if(result == 1) { 
		return "redirect:/community/recycleLife/selectAll";
	} else { 
		return "redirect:/community/recycleLifeComment/delete?lifeComment_num=" + vo.getLifeComment_num(); 
		} 
	}
	
	@PostMapping("/community/recycleLifeComment/updateOK") 
	public String updateOK(RecycleLifeCommentVO vo) {
	log.info("/community/recycleLifeComment/updateOK"); 
	log.info("vo : {}", vo);
	
	int result = service.updateOK(vo); 
	log.info("result : {}", result);
	
	if(result == 1) { 
		return "redirect:/community/recycleLife/selectAll";
	} else { 
		return "redirect:/community/recycleLifeComment/update?lifeComment_num=" + vo.getLifeComment_num(); 
		}	
	}
	
	@RequestMapping(value="/deleteModel", method={RequestMethod.GET, RequestMethod.POST})
		public void deleteModel() {
			log.info("댓글 삭제 모달창으로 이동");
		}
	}
	
	
