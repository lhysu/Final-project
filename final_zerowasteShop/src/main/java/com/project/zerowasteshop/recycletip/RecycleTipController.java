package com.project.zerowasteshop.recycletip;

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
public class RecycleTipController {

    @Autowired
    private RecycleTipService service;

    // application.properties에서 설정한 변수(file.dir)를 DI
    @Value("${file.dir}")
    private String realPath;

    @GetMapping("/community/recycletip/insert")
    public String rt_insert() {
        log.info("/community/recycletip/insert 요청");
        return "community/recycletip/insert";
    }

    @GetMapping("/community/recycletip/update")
    public String rt_update(int recycleTip_num, Model model) {
        log.info("/community/recycletip/update 요청");
        log.info("recycleTip_num: {}", recycleTip_num);

        RecycleTipVO vo = new RecycleTipVO();
        vo.setRecycleTip_num(recycleTip_num);

        RecycleTipVO vo2 = service.selectOne(vo);
        log.info("vo2: {}", vo2);

        model.addAttribute("vo2", vo2);

        return "community/recycletip/update";
    }

    @GetMapping("/community/recycletip/delete")
    public String rt_delete(int recycleTip_num, Model model) {
        log.info("/community/recycletip/delete 요청");
        log.info("recycleTip_num: {}", recycleTip_num);

        model.addAttribute("recycleTip_num", recycleTip_num);

        return "community/recycletip/delete";
    }

    @GetMapping("/community/recycletip/selectAll")
    public String rt_selectAll(Model model,
                               @RequestParam(defaultValue = "1") int cpage,
                               @RequestParam(defaultValue = "5") int pageBlock) {
        log.info("/community/recycletip/selectAll 요청");
        log.info("cpage: {}", cpage);
        log.info("pageBlock: {}", pageBlock);

        List<RecycleTipVO> list = service.selectAllPageBlock(cpage, pageBlock);
        log.info("list.size(): {}", list.size());

        model.addAttribute("list", list);

        // 총 페이지 수 계산
        int total_rows = service.getTotalRows();
        log.info("total_rows: {}", total_rows);
        int totalPageCount = (int) Math.ceil((double) total_rows / pageBlock);
        log.info("totalPageCount: {}", totalPageCount);

        model.addAttribute("totalPageCount", totalPageCount);

        return "community/recycletip/selectAll";
    }

    @GetMapping("/community/recycletip/searchList")
    public String rt_searchList(Model model,
                                @RequestParam String searchField,
                                @RequestParam String searchWord,
                                @RequestParam(defaultValue = "1") int cpage,
                                @RequestParam(defaultValue = "5") int pageBlock) {
        log.info("/community/recycletip/searchList 요청");
        log.info("searchField: {}", searchField);
        log.info("searchWord: {}", searchWord);
        log.info("cpage: {}", cpage);
        log.info("pageBlock: {}", pageBlock);

        List<RecycleTipVO> list = service.searchListPageBlock(searchField, searchWord, cpage, pageBlock);
        log.info("list.size(): {}", list.size());

        model.addAttribute("list", list);

        int total_rows = service.getSearchTotalRows(searchField, searchWord);
        log.info("total_rows: {}", total_rows);
        int totalPageCount = (int) Math.ceil((double) total_rows / pageBlock);
        log.info("totalPageCount: {}", totalPageCount);

        model.addAttribute("totalPageCount", totalPageCount);

        return "community/recycletip/selectAll";
    }

    @GetMapping("/community/recycletip/selectOne")
    public String rt_selectOne(int recycleTip_num, Model model) {
        log.info("/community/recycletip/selectOne 요청");
        log.info("recycleTip_num: {}", recycleTip_num);

        RecycleTipVO vo = new RecycleTipVO();
        vo.setRecycleTip_num(recycleTip_num);

        RecycleTipVO vo2 = service.selectOne(vo);
        log.info("vo2: {}", vo2);

        if (vo2 != null) {
            // 조회수 증가
            service.incrementViewCount(vo2.getRecycleTip_num());
            vo2.setRecycleTip_views(vo2.getRecycleTip_views() + 1);
        }

        model.addAttribute("vo2", vo2);

        return "community/recycletip/selectOne";
    }

    @PostMapping("/community/recycletip/insertOK")
    public String rt_insertOK(RecycleTipVO vo) throws IllegalStateException, IOException {
        log.info("/community/recycletip/insertOK 요청");
        log.info("vo: {}", vo);

        log.info("파일 저장 경로: {}", realPath);

        MultipartFile file = vo.getFile();
        String originName = file.getOriginalFilename();
        log.info("originName: {}", originName);

        if (originName == null || originName.isEmpty()) {
            vo.setRecycleTip_img("default.png");
        } else {
            String saveName = "img_" + System.currentTimeMillis()
                    + originName.substring(originName.lastIndexOf("."));
            log.info("saveName: {}", saveName);
            vo.setRecycleTip_img(saveName);

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
        log.info("result: {}", result);
        if (result == 1) {
            return "redirect:/community/recycletip/selectAll";
        } else {
            return "redirect:/community/recycletip/insert";
        }
    }

    @PostMapping("/community/recycletip/updateOK")
    public String rt_updateOK(RecycleTipVO vo) throws IllegalStateException, IOException {
        log.info("/community/recycletip/updateOK 요청");
        log.info("vo: {}", vo);

        log.info("파일 저장 경로: {}", realPath);

        MultipartFile file = vo.getFile();
        String originName = file.getOriginalFilename();
        log.info("originName: {}", originName);

        if (originName == null || originName.isEmpty()) {
            // 기존 이미지 유지
            vo.setRecycleTip_img(vo.getRecycleTip_img());
        } else {
            String saveName = "img_" + System.currentTimeMillis()
                    + originName.substring(originName.lastIndexOf("."));
            log.info("saveName: {}", saveName);
            vo.setRecycleTip_img(saveName);

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
        log.info("result: {}", result);
        if (result == 1) {
            return "redirect:/community/recycletip/selectOne?recycleTip_num=" + vo.getRecycleTip_num();
        } else {
            return "redirect:/community/recycletip/update?recycleTip_num=" + vo.getRecycleTip_num();
        }
    }

    @PostMapping("/community/recycletip/deleteOK")
    public String rt_deleteOK(int recycleTip_num) {
        log.info("/community/recycletip/deleteOK 요청");
        log.info("recycleTip_num: {}", recycleTip_num);

        RecycleTipVO vo = new RecycleTipVO();
        vo.setRecycleTip_num(recycleTip_num);

        int result = service.deleteOK(vo);
        log.info("result: {}", result);
        if (result == 1) {
            return "redirect:/community/recycletip/selectAll";
        } else {
            return "redirect:/community/recycletip/delete?recycleTip_num=" + recycleTip_num;
        }
    }
}
