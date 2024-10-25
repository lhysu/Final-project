package com.project.zerowasteshop.recyclelife;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.project.zerowasteshop.delivery.DeliveryVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class RecycleLifeController {
	
	@Autowired
	RecycleLifeService service;
	
	@Value("${file.dir}")
	private String realPath;
	
	@GetMapping("/community/recycleLife/rl_selectAll")
	public String rl_selectAll(Model model,
			@RequestParam(defaultValue = "1") int cpage,
            @RequestParam(defaultValue = "5") int pageBlock) {
		log.info("/community/recycleLife/rl_selectAll");
		log.info("cpage : {}", cpage);
        log.info("pageBlock : {}", pageBlock);
		
//		List<RecycleLifeVO> list = service.selectAll();
		List<RecycleLifeVO> list = service.selectAllPageBlock(cpage, pageBlock);
		log.info("list.size() : {}", list.size());
		
		model.addAttribute("list", list);
		
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
		
		return "community/recycleLife/rl_selectAll";
	}
	
	@GetMapping("/community/recycleLife/rl_selectOne")
	public String rl_selectOne(RecycleLifeVO vo, Model model) {
		log.info("/community/recycleLife/rl_selectOne");
		log.info("vo : {}", vo);
		
		RecycleLifeVO vo2 = service.rl_selectOne(vo);
		log.info("vo2", vo2);
		
		if (vo2 == null) {
	        // vo2 값이 null 인지 확인 로그 출력 또는 예외 처리
	        System.out.println("vo2 is null");
	    } else {
	    	//조회수 증가 로직, 중복 방지 기능은 구현 X
	    	service.increaseViews(vo2.getRecycleLife_num());
	    }
			
		model.addAttribute("vo2", vo2);
		
		return "community/recycleLife/rl_selectOne";
	}
	
	@GetMapping("/community/recycleLife/rl_searchList")
	public String d_searchList(Model model,
			@RequestParam(defaultValue = "recycleLife_num") String searchKey,
			@RequestParam(defaultValue = "1") String searchWord) {
		log.info("/community/recycleLife/rl_searchList");
		log.info("searchWord : {}", searchWord);
		log.info("searchKey : {}", searchKey);
		
		List<RecycleLifeVO> list = service.searchList(searchKey, searchWord);
		log.info("list.size() : {}", list.size());
		
		model.addAttribute("list", list);
		
		return "community/recycleLife/rl_searchList";
	}
	
	@GetMapping("/community/recycleLife/rl_insert")
	public String rl_insert() {
		log.info("/community/recycleLife/rl_insert");
		
		return "community/recycleLife/rl_insert";
	}
	
	@GetMapping("/community/recycleLife/rl_delete")
	public String rl_delete() {
		log.info("/community/recycleLife/rl_delete");
		
		return "community/recycleLife/rl_delete";
	}
	
	@GetMapping("/community/recycleLife/rl_update")
	public String rl_update(RecycleLifeVO vo, Model model) {
		log.info("/community/recycleLife/rl_update");
		log.info("vo : {}", vo);
		
		RecycleLifeVO vo2 = service.rl_selectOne(vo);
		
		log.info("vo2:{}", vo2);

		model.addAttribute("vo2", vo2);
		
		if(vo2 == null) {
			log.info("vo2 is null");		
		}
	
		return "community/recycleLife/rl_update";
	}

	@PostMapping("/community/recycleLife/rl_insertOK")
	public String rl_insertOK(RecycleLifeVO vo) throws IllegalStateException, IOException {
		log.info("/community/recycleLife/rl_insertOK");
		log.info("vo : {}", vo);

		log.info(realPath);

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
            return "redirect:/community/event/selectAll";
        } else {
            return "redirect:/community/event/insert";
        }
	}
	
	@PostMapping("/community/recycleLife/rl_updateOK")
	public String rl_updateOK(RecycleLifeVO vo) throws IllegalStateException, IOException {
		log.info("/community/recycleLife/rl_updateOK");
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
            return "redirect:/community/recycleLife/rl_selectOne?recycleLife_num=" + vo.getRecycleLife_num();
        } else {
            return "redirect:/community/recycleLife/rl_update?recycleLife_num=" + vo.getRecycleLife_num();
        }
    }
	
	
	@PostMapping("/community/recycleLife/rl_deleteOK")
	public String rl_deleteOK(RecycleLifeVO vo) {
		log.info("/community/recycleLife/rl_deleteOK");
		log.info("vo : {}", vo);
		
		int result = service.deleteOK(vo);
		log.info("result : {}", result);
		if (result == 1) {
			return "redirect:/community/recycleLife/rl_selectAll";
		} else {
			return "redirect:/community/recycleLife/rl_delete?recycleLife_num=" + vo.getRecycleLife_num();
		}
	}
	
}
