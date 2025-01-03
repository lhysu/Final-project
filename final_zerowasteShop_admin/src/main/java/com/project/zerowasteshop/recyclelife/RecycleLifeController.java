package com.project.zerowasteshop.recyclelife;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.project.zerowasteshop.recyclelifecomment.RecycleLifeCommentService;
import com.project.zerowasteshop.recyclelifecomment.RecycleLifeCommentVO;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class RecycleLifeController {
	
	@Autowired
	RecycleLifeService service;
	
	@Autowired
	RecycleLifeCommentService commentService;
	
	@Value("${file.dir}")
	private String realPath;
		
	@GetMapping("/community/recycleLife/selectAll")
	public String selectAll(Model model,
			@RequestParam(defaultValue = "1") int cpage,
            @RequestParam(defaultValue = "10") int pageBlock) {
		log.info("/community/recycleLife/selectAll");
		log.info("cpage : {}", cpage);
        log.info("pageBlock : {}", pageBlock);
		
//		List<RecycleLifeVO> list = service.selectAll();
		List<RecycleLifeVO> list = service.selectAllPageBlock(cpage, pageBlock);
		log.info("list.size() : {}", list.size());
		
		model.addAttribute("list", list);
		
		//DB로부터 얻은 검색결과의 모든 행수
		int total_rows=service.getTotalRows();	
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
		model.addAttribute("cpage", cpage);
		
 		// 페이지네이션 범위 계산	
 	    int startPage = Math.max(1, cpage - 4); // 현재 페이지 기준으로 시작 페이지
 	    int endPage = Math.min(totalPageCount, startPage + 9); // 10개 페이지 표시

 	    model.addAttribute("startPage", startPage);
 	    model.addAttribute("endPage", endPage);
        
        // 조회수 Top3 게시물 조회
        List<RecycleLifeVO> post = service.selectTopViews();
		model.addAttribute("post", post);
		log.info("post : {}", post);
		
		return "community/recycleLife/selectAll";
	}
	
	@GetMapping("/community/recycleLife/selectOne")
	public String selectOne(RecycleLifeVO vo, Model model,
			@RequestParam int recycleLife_num) {
		log.info("/community/recycleLife/selectOne");
		log.info("vo : {}", vo);
		
		RecycleLifeVO vo2 = service.selectOne(vo);
		log.info("vo2:{}", vo2);
		model.addAttribute("vo2", vo2);
		// 댓글 목록 조회
		List<RecycleLifeCommentVO> list = commentService.selectAll(vo.getRecycleLife_num());
		log.info("list : {}", list);
		model.addAttribute("list", list);
		
		if (vo2 == null) {
	        // vo2 값이 null 인지 확인 로그 출력 또는 예외 처리
	        System.out.println("vo2 is null");
	    } else {
	    	// 조회수 증가
	    	service.increaseViews(vo2.getRecycleLife_num());
	    }			
		
		
		
		int likes = service.getLikeCount(recycleLife_num);
        model.addAttribute("recycleLife_num", recycleLife_num);
        model.addAttribute("recycleLife_likes", likes);
        
        // 게시글 가져오기 (가정)
        RecycleLifeVO post = service.getPost(recycleLife_num);
        model.addAttribute("post", post);
        
        // 해당 게시글의 댓글 가져오기
        List<RecycleLifeCommentVO> comments = commentService.getCommentsByPost(recycleLife_num);
        model.addAttribute("comments", comments);
        
		return "community/recycleLife/selectOne";
	}
	
	// 좋아요 클릭 업데이트 로직
	@PostMapping("/community/recycleLife/selectOne")
	public ResponseEntity<Integer> toggleLike(@RequestParam("recycleLife_num") int recycleLife_num,
            @RequestParam("isLiked") boolean isLiked) {
		int updatedLikes = service.toggleLike(recycleLife_num, isLiked);
		return ResponseEntity.ok(updatedLikes);
	}
	
	@GetMapping("/community/recycleLife/searchList")
	public String searchList(Model model,
			@RequestParam(defaultValue = "recycleLife_num") String searchKey,
			@RequestParam(defaultValue = "")String searchWord,
			@RequestParam(defaultValue = "1")int cpage,
			@RequestParam(defaultValue = "10")int pageBlock) {
		log.info("/community/recycleLife/searchList");
		log.info("searchWord : {}", searchWord);
		log.info("searchKey : {}", searchKey);
		
		List<RecycleLifeVO> list = service.searchListPageBlock(searchKey,searchWord,cpage,pageBlock);
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
		model.addAttribute("cpage", cpage);
		model.addAttribute("list", list);
		
 		// 페이지네이션 범위 계산	
 	    int startPage = Math.max(1, cpage - 4); // 현재 페이지 기준으로 시작 페이지
 	    int endPage = Math.min(totalPageCount, startPage + 9); // 10개 페이지 표시

 	    model.addAttribute("startPage", startPage);
 	    model.addAttribute("endPage", endPage);
		
		return "community/recycleLife/selectAll";
	}
	
	@GetMapping("/community/recycleLife/insert")
	public String insert() {
		log.info("/community/recycleLife/insert");
		
		return "community/recycleLife/insert";
	}
	
	@GetMapping("/community/recycleLife/delete")
	public String delete() {
		log.info("/community/recycleLife/delete");
		
		return "community/recycleLife/delete";
	}
	
	@GetMapping("/community/recycleLife/update")
	public String update(RecycleLifeVO vo, Model model) {
		log.info("/community/recycleLife/update");
		log.info("vo : {}", vo);
		
		RecycleLifeVO vo2 = service.selectOne(vo);
		
		log.info("vo2:{}", vo2);

		model.addAttribute("vo2", vo2);
		
		return "community/recycleLife/update";
	}

	@PostMapping("/community/recycleLife/insertOK")
	public String insertOK(RecycleLifeVO vo, HttpSession session) throws IllegalStateException, IOException {
		log.info("/community/recycleLife/insertOK");
		log.info("vo : {}", vo);

		log.info(realPath);
		
		String member_id = (String) session.getAttribute("user_id");

        MultipartFile file = vo.getFile();
        String originName = file.getOriginalFilename();
        log.info("originName:{}", originName);

        if (originName == null || originName.isEmpty()) {
            vo.setRecycleLife_img("default.png");
        } else {
            String saveName = "img_" + System.currentTimeMillis()
                    + originName.substring(originName.lastIndexOf("."));
            log.info("saveName : {}", saveName);
            vo.setRecycleLife_img(saveName);

            File f = new File(realPath, saveName);
            file.transferTo(f);

            // 썸네일 생성
            BufferedImage originalImage = ImageIO.read(f);
            BufferedImage thumbnailImage = new BufferedImage(50, 50, BufferedImage.TYPE_3BYTE_BGR);
            Graphics2D graphic = thumbnailImage.createGraphics();
            graphic.drawImage(originalImage, 0, 0, 50, 50, null);

            File thumbnailFile = new File(realPath, "thumb_" + saveName);
            ImageIO.write(thumbnailImage, saveName.substring(saveName.lastIndexOf(".") + 1), thumbnailFile);
        }

        int result = service.insertOK(vo);
        log.info("result : {}", result);
        
        if (result == 1) {
            return "redirect:/community/recycleLife/selectAll";
        } else {
            return "redirect:/community/recycleLife/insert";
        }
	}
	
	@PostMapping("/community/recycleLife/updateOK")
	public String updateOK(RecycleLifeVO vo) throws IllegalStateException, IOException {
		log.info("/community/recycleLife/updateOK");
		log.info("vo : {}", vo);
		
		log.info(realPath);

        MultipartFile file = vo.getFile();
        String originName = file.getOriginalFilename();
        log.info("originName : {}", originName);

        if (originName == null || originName.isEmpty()) {
            vo.setRecycleLife_img(vo.getRecycleLife_img());
        } else {
            String saveName = "img_" + System.currentTimeMillis()
                    + originName.substring(originName.lastIndexOf("."));
            log.info("saveName:{}", saveName);
            vo.setRecycleLife_img(saveName);

            File f = new File(realPath, saveName);
            file.transferTo(f);

            // 썸네일 생성
            BufferedImage originalImage = ImageIO.read(f);
            BufferedImage thumbnailImage = new BufferedImage(50, 50, BufferedImage.TYPE_3BYTE_BGR);
            Graphics2D graphic = thumbnailImage.createGraphics();
            graphic.drawImage(originalImage, 0, 0, 50, 50, null);

            File thumbnailFile = new File(realPath, "thumb_" + saveName);
            ImageIO.write(thumbnailImage, saveName.substring(saveName.lastIndexOf(".") + 1), thumbnailFile);
        }

        int result = service.updateOK(vo);
        log.info("result : {}", result);
        if (result == 1) {
            return "redirect:/community/recycleLife/selectOne?recycleLife_num=" + vo.getRecycleLife_num();
        } else {
            return "redirect:/community/recycleLife/update?recycleLife_num=" + vo.getRecycleLife_num();
        }
    }
	
	
	@PostMapping("/community/recycleLife/deleteOK")
	public String deleteOK(RecycleLifeVO vo) {
		log.info("/community/recycleLife/deleteOK");
		log.info("vo : {}", vo);
		
		int result = service.deleteOK(vo);
		log.info("result : {}", result);
		if (result == 1) {
			return "redirect:/community/recycleLife/selectAll";
		} else {
			return "redirect:/community/recycleLife/delete?recycleLife_num=" + vo.getRecycleLife_num();
		}
	}
	
}
