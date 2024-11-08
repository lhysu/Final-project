package com.project.zerowasteshop.notice;

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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class NoticeController {

    @Autowired
    private NoticeService service;

    // application.properties에서 설정한 변수(file.dir)를 DI
    @Value("${file.dir}")
    private String realPath;

    @GetMapping("/community/notice/insert")
    public String n_insert() {
        log.info("/community/notice/insert 요청");
        return "community/notice/insert";
    }

    @GetMapping("/community/notice/update")
    public String n_update(NoticeVO vo, Model model) {
        log.info("/community/notice/update 요청");
        log.info("vo:{}", vo);

        NoticeVO vo2 = service.selectOne(vo);
        log.info("vo2:{}", vo2);

        model.addAttribute("vo2", vo2);

        return "community/notice/update";
    }


    @GetMapping("/community/notice/delete")
    public String n_delete() {
        log.info("/community/notice/delete 요청");
        return "community/notice/delete";
    }

    @GetMapping("/community/notice/selectOne")
    public String n_selectOne(NoticeVO vo, Model model) {
        log.info("/community/notice/selectOne 요청");
        log.info("vo:{}", vo);

        NoticeVO vo2 = service.selectOne(vo);
        log.info("vo2:{}", vo2);

        if (vo2 != null) {
            // 조회수가 증가하도록 서비스 호출
            service.incrementViewCount(vo2.getNotice_num());
        }

        model.addAttribute("vo2", vo2);

        return "community/notice/selectOne";
    }

	@GetMapping("/community/notice/selectAll")
	public String n_selectAll(Model model,
	                          @RequestParam(defaultValue = "1") int cpage,
	                          @RequestParam(defaultValue = "5") int pageBlock) {
	    log.info("/community/notice/selectAll 요청");
	    log.info("cpage:{}", cpage);
	    log.info("pageBlock:{}", pageBlock);
	
	    // 페이지 번호 유효성 검사
	    if (cpage < 1) cpage = 1;
	
	    // 공지사항 목록 조회
	    List<NoticeVO> list = service.selectAllPageBlock(cpage, pageBlock);
	    log.info("list.size():{}", list.size());
	
	    model.addAttribute("list", list);
	
	    // 총 페이지 수 계산
	    int total_rows = service.getTotalRows();
	    log.info("total_rows:{}", total_rows);
	
	    int totalPageCount = (total_rows + pageBlock - 1) / pageBlock;
	    if (totalPageCount < 1) totalPageCount = 1;
	    log.info("totalPageCount:{}", totalPageCount);
	
	    // 페이지네이션의 시작 페이지와 끝 페이지 계산
	    int pageGroupSize = 5; // 페이지 그룹 크기
	    int startPage = ((cpage - 1) / pageGroupSize) * pageGroupSize + 1;
	    int endPage = startPage + pageGroupSize - 1;
	    if (endPage > totalPageCount) endPage = totalPageCount;
	
	    // 모델에 필요한 속성 추가
	    model.addAttribute("totalPageCount", totalPageCount);
	    model.addAttribute("cpage", cpage);
	    model.addAttribute("startPage", startPage);
	    model.addAttribute("endPage", endPage);
	
	    return "community/notice/selectAll";
	}
	
	@GetMapping("/community/notice/searchList")
	public String n_searchList(Model model,
	                           @RequestParam(defaultValue = "") String searchWord,
	                           @RequestParam(defaultValue = "1") int cpage,
	                           @RequestParam(defaultValue = "5") int pageBlock) {
	    log.info("/community/notice/searchList 요청");
	    log.info("searchWord:{}", searchWord);
	    log.info("cpage:{}", cpage);
	    log.info("pageBlock:{}", pageBlock);
	
	    // 페이지 번호 유효성 검사
	    if (cpage < 1) cpage = 1;
	
	    // 검색 결과 조회
	    List<NoticeVO> list = service.searchListPageBlock(searchWord, cpage, pageBlock);
	    log.info("list.size():{}", list.size());
	
	    model.addAttribute("list", list);
	
	    int total_rows = service.getSearchTotalRows(searchWord);
	    log.info("total_rows:{}", total_rows);
	
	    int totalPageCount = (total_rows + pageBlock - 1) / pageBlock;
	    if (totalPageCount < 1) totalPageCount = 1;
	    log.info("totalPageCount:{}", totalPageCount);
	
	    // 페이지네이션의 시작 페이지와 끝 페이지 계산
	    int pageGroupSize = 5;
	    int startPage = ((cpage - 1) / pageGroupSize) * pageGroupSize + 1;
	    int endPage = startPage + pageGroupSize - 1;
	    if (endPage > totalPageCount) endPage = totalPageCount;
	
	    // 모델에 필요한 속성 추가
	    model.addAttribute("totalPageCount", totalPageCount);
	    model.addAttribute("cpage", cpage);
	    model.addAttribute("startPage", startPage);
	    model.addAttribute("endPage", endPage);
	    model.addAttribute("searchWord", searchWord);
	
	    return "community/notice/selectAll";
	}

    @PostMapping("/community/notice/insertOK")
    public String n_insertOK(NoticeVO vo) throws IllegalStateException, IOException {
        log.info("/community/notice/insertOK 요청");
        log.info("vo:{}", vo);

        log.info("파일 저장 경로: {}", realPath);

        MultipartFile file = vo.getFile();
        String originName = file.getOriginalFilename();
        log.info("originName:{}", originName);

        if (originName == null || originName.isEmpty()) {
            vo.setNotice_img("default.png");
        } else {
            String saveName = "img_" + System.currentTimeMillis()
                    + originName.substring(originName.lastIndexOf("."));
            log.info("saveName:{}", saveName);
            vo.setNotice_img(saveName);

            File f = new File(realPath, saveName);
            file.transferTo(f);

            // 썸네일 생성
            BufferedImage originalImage = ImageIO.read(f);
            BufferedImage thumbnailImage = new BufferedImage(50, 50, BufferedImage.TYPE_3BYTE_BGR);
            Graphics2D graphic = thumbnailImage.createGraphics();
            graphic.drawImage(originalImage, 0, 0, 50, 50, null);
            graphic.dispose();

            File thumbnailFile = new File(realPath, "thumb_" + saveName);
            ImageIO.write(thumbnailImage, saveName.substring(saveName.lastIndexOf(".") + 1), thumbnailFile);
        }

        int result = service.insertOK(vo);
        log.info("result:{}", result);
        if (result == 1) {
            return "redirect:/community/notice/selectAll";
        } else {
            return "redirect:/community/notice/insert";
        }
    }

    @PostMapping("/community/notice/deleteOK")
    public String n_deleteOK(NoticeVO vo) {
        log.info("/community/notice/deleteOK 요청");
        log.info("vo:{}", vo);

        int result = service.deleteOK(vo);
        log.info("result:{}", result);
        if (result == 1) {
            return "redirect:/community/notice/selectAll";
        } else {
            return "redirect:/community/notice/delete?notice_num=" + vo.getNotice_num();
        }
    }

    @PostMapping("/community/notice/updateOK")
    public String n_updateOK(NoticeVO vo) throws IllegalStateException, IOException {
        log.info("/community/notice/updateOK 요청");
        log.info("vo:{}", vo);

        log.info("파일 저장 경로: {}", realPath);

        MultipartFile file = vo.getFile();
        String originName = file.getOriginalFilename();
        log.info("originName:{}", originName);

        if (originName == null || originName.isEmpty()) {
            // 기존 이미지 유지
            vo.setNotice_img(vo.getNotice_img());
        } else {
            String saveName = "img_" + System.currentTimeMillis()
                    + originName.substring(originName.lastIndexOf("."));
            log.info("saveName:{}", saveName);
            vo.setNotice_img(saveName);

            File f = new File(realPath, saveName);
            file.transferTo(f);

            // 썸네일 생성 
            BufferedImage originalImage = ImageIO.read(f);
            BufferedImage thumbnailImage = new BufferedImage(50, 50, BufferedImage.TYPE_3BYTE_BGR);
            Graphics2D graphic = thumbnailImage.createGraphics();
            graphic.drawImage(originalImage, 0, 0, 50, 50, null);
            graphic.dispose();

            File thumbnailFile = new File(realPath, "thumb_" + saveName);
            ImageIO.write(thumbnailImage, saveName.substring(saveName.lastIndexOf(".") + 1), thumbnailFile);
        }

        int result = service.updateOK(vo);
        log.info("result:{}", result);
        if (result == 1) {
            return "redirect:/community/notice/selectOne?notice_num=" + vo.getNotice_num();
        } else {
            return "redirect:/community/notice/update?notice_num=" + vo.getNotice_num();
        }
    }
}
