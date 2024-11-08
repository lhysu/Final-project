package com.project.zerowasteshop.event;

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
public class EventController {

    @Autowired
    private EventService service;

    // application.properties에서 설정한 변수(file.dir)를 DI
    @Value("${file.dir}")
    private String realPath;

    @GetMapping("/community/event/insert")
    public String e_insert() {
        log.info("/community/event/insert");
        return "community/event/insert";
    }

    @GetMapping("/community/event/update")
    public String e_update(EventVO vo, Model model) {
        log.info("/community/event/update");
        log.info("vo:{}", vo);

        EventVO vo2 = service.selectOne(vo);
        log.info("vo2:{}", vo2);

        model.addAttribute("vo2", vo2);

        return "community/event/update";
    }

    @GetMapping("/community/event/delete")
    public String e_delete() {
        log.info("/event/delete");
        return "community/event/delete";
    }

    @GetMapping("/community/event/selectOne")
    public String e_selectOne(EventVO vo, Model model) {
        log.info("/event/selectOne");
        log.info("vo:{}", vo);

        EventVO vo2 = service.selectOne(vo);
        log.info("vo2:{}", vo2);
        
        if (vo2 != null) {
            // 조회수가 증가하도록 서비스 호출
            service.incrementViewCount(vo2.getEvent_num());
        }

        model.addAttribute("vo2", vo2);

        return "community/event/selectOne";
    }

    @GetMapping("/community/event/selectAll")
    public String e_selectAll(Model model,
                              @RequestParam(defaultValue = "1") int cpage,
                              @RequestParam(defaultValue = "5") int pageBlock) {
        log.info("/community/event/selectAll");
        log.info("cpage:{}", cpage);    
        log.info("pageBlock:{}", pageBlock);

        // 페이지 번호가 1보다 작은 경우 1로 설정
        if (cpage < 1) cpage = 1;

        // 이벤트 목록 조회
        List<EventVO> list = service.selectAllPageBlock(cpage, pageBlock);
        log.info("list.size():{}", list.size());

        model.addAttribute("list", list);

        // 총 페이지 수 계산
        int total_rows = service.getTotalRows();
        log.info("total_rows:{}", total_rows);

        int totalPageCount = (total_rows + pageBlock - 1) / pageBlock; // 올바른 총 페이지 수 계산
        if (totalPageCount < 1) totalPageCount = 1; // 총 페이지 수가 최소 1이 되도록 보장
        log.info("totalPageCount:{}", totalPageCount);

        // 페이지네이션의 시작 페이지와 끝 페이지 계산
        int pageGroupSize = 5; // 페이지 그룹 크기 (한 번에 표시할 페이지 수)
        int startPage = ((cpage - 1) / pageGroupSize) * pageGroupSize + 1;
        int endPage = startPage + pageGroupSize - 1;
        if (endPage > totalPageCount) endPage = totalPageCount;

        // 모델에 필요한 속성 추가
        model.addAttribute("totalPageCount", totalPageCount);
        model.addAttribute("cpage", cpage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        return "community/event/selectAll";
    }

    @GetMapping("/community/event/searchList")
    public String e_searchList(Model model,
                                @RequestParam(defaultValue = "") String searchWord,
                                @RequestParam(defaultValue = "1") int cpage,
                                @RequestParam(defaultValue = "5") int pageBlock) {
        log.info("/event/searchListPageBlock");
        log.info("searchWord:{}", searchWord);
        log.info("cpage:{}", cpage);
        log.info("pageBlock:{}", pageBlock);

        // 페이지 번호가 1보다 작은 경우 1로 설정
        if (cpage < 1) cpage = 1;

        // 검색 결과 조회
        List<EventVO> list = service.searchListPageBlock(searchWord, cpage, pageBlock);
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

        return "community/event/selectAll";
    }

    @PostMapping("/community/event/insertOK")
    public String e_insertOK(EventVO vo) throws IllegalStateException, IOException {
        log.info("/community/event/insertOK");
        log.info("vo:{}", vo);

        log.info(realPath);

        MultipartFile file = vo.getFile();
        String originName = file.getOriginalFilename();
        log.info("originName:{}", originName);

        if (originName == null || originName.isEmpty()) {
            vo.setEvent_img("default.png");
        } else {
            String saveName = "img_" + System.currentTimeMillis()
                    + originName.substring(originName.lastIndexOf("."));
            log.info("saveName:{}", saveName);
            vo.setEvent_img(saveName);

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
        log.info("result:{}", result);
        if (result == 1) {
            return "redirect:/community/event/selectAll";
        } else {
            return "redirect:/community/event/insert";
        }
    }

    @PostMapping("/community/event/deleteOK")
    public String e_deleteOK(EventVO vo) {
        log.info("/community/event/deleteOK");
        log.info("vo:{}", vo);

        int result = service.deleteOK(vo);
        log.info("result:{}", result);
        if (result == 1) {
            return "redirect:/community/event/selectAll";
        } else {
            return "redirect:/community/event/delete?event_num=" + vo.getEvent_num();
        }
    }

    @PostMapping("/community/event/updateOK")
    public String e_updateOK(EventVO vo) throws IllegalStateException, IOException {
        log.info("/community/event/updateOK");
        log.info("vo:{}", vo);

        log.info(realPath);

        MultipartFile file = vo.getFile();
        String originName = file.getOriginalFilename();
        log.info("originName:{}", originName);

        if (originName == null || originName.isEmpty()) {
            vo.setEvent_img(vo.getEvent_img());
        } else {
            String saveName = "img_" + System.currentTimeMillis()
                    + originName.substring(originName.lastIndexOf("."));
            log.info("saveName:{}", saveName);
            vo.setEvent_img(saveName);

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
        log.info("result:{}", result);
        if (result == 1) {
            return "redirect:/community/event/selectOne?event_num=" + vo.getEvent_num();
        } else {
            return "redirect:/community/event/update?event_num=" + vo.getEvent_num();
        }
    }
}
