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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.zerowasteshop.recycletipcomment.RecycleTipCommentService;
import com.project.zerowasteshop.recycletipcomment.RecycleTipCommentVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class RecycleTipController {

    @Autowired
    private RecycleTipService service;

    @Autowired
    private RecycleTipCommentService commentService; // 댓글 서비스 주입

    // application.properties에서 설정한 변수(file.dir)를 DI
    @Value("${file.dir}")
    private String realPath;

    @GetMapping("/community/recycleTip/insert")
    public String rt_insert() {
        log.info("/community/recycleTip/insert 요청");
        return "community/recycleTip/insert";
    }

    @GetMapping("/community/recycleTip/update")
    public String rt_update(@RequestParam("recycleTip_num") int recycleTip_num, Model model) {
        log.info("/community/recycleTip/update 요청");
        log.info("recycleTip_num: {}", recycleTip_num);

        RecycleTipVO vo = new RecycleTipVO();
        vo.setRecycleTip_num(recycleTip_num);

        RecycleTipVO vo2 = service.selectOne(vo);
        log.info("vo2: {}", vo2);

        model.addAttribute("vo2", vo2);

        return "community/recycleTip/update";
    }

    @GetMapping("/community/recycleTip/delete")
    public String rt_delete(@RequestParam("recycleTip_num") int recycleTip_num, Model model) {
        log.info("/community/recycleTip/delete 요청");
        log.info("recycleTip_num: {}", recycleTip_num);

        model.addAttribute("recycleTip_num", recycleTip_num);

        return "community/recycleTip/delete";
    }

    @GetMapping("/community/recycleTip/selectAll")
    public String rt_selectAll(Model model,
                               @RequestParam(defaultValue = "1") int cpage,
                               @RequestParam(defaultValue = "5") int pageBlock) {
        log.info("/community/recycleTip/selectAll 요청");
        log.info("cpage: {}", cpage);
        log.info("pageBlock: {}", pageBlock);

        List<RecycleTipVO> list = service.selectAllPageBlock(cpage, pageBlock);
        log.info("list.size(): {}", list.size());

        model.addAttribute("list", list);
        model.addAttribute("cpage", cpage); // cpage를 모델에 추가

        // 총 페이지 수 계산
        int total_rows = service.getTotalRows();
        log.info("total_rows: {}", total_rows);
        int totalPageCount = (int) Math.ceil((double) total_rows / pageBlock);
        log.info("totalPageCount: {}", totalPageCount);

        model.addAttribute("totalPageCount", totalPageCount);

        return "community/recycleTip/selectAll";
    }

    @GetMapping("/community/recycleTip/searchList")
    public String rt_searchList(Model model,
                                @RequestParam String searchField,
                                @RequestParam String searchWord,
                                @RequestParam(defaultValue = "1") int cpage,
                                @RequestParam(defaultValue = "5") int pageBlock) {
        log.info("/community/recycleTip/searchList 요청");
        log.info("searchField: {}", searchField);
        log.info("searchWord: {}", searchWord);
        log.info("cpage: {}", cpage);
        log.info("pageBlock: {}", pageBlock);

        List<RecycleTipVO> list = service.searchListPageBlock(searchField, searchWord, cpage, pageBlock);
        log.info("list.size(): {}", list.size());

        model.addAttribute("list", list);
        model.addAttribute("cpage", cpage); // cpage를 모델에 추가

        int total_rows = service.getSearchTotalRows(searchField, searchWord);
        log.info("total_rows: {}", total_rows);
        int totalPageCount = (int) Math.ceil((double) total_rows / pageBlock);
        log.info("totalPageCount: {}", totalPageCount);

        model.addAttribute("totalPageCount", totalPageCount);

        return "community/recycleTip/selectAll";
    }

    @GetMapping("/community/recycleTip/selectOne")
    public String rt_selectOne(@RequestParam("recycleTip_num") int recycleTip_num, Model model) {
        log.info("/community/recycleTip/selectOne 요청");
        log.info("recycleTip_num: {}", recycleTip_num);

        RecycleTipVO vo = new RecycleTipVO();
        vo.setRecycleTip_num(recycleTip_num);

        RecycleTipVO vo2 = service.selectOne(vo);
        log.info("vo2: {}", vo2);

        if (vo2 != null) {
            // 조회수 증가
            service.incrementViewCount(recycleTip_num);
        }

        model.addAttribute("vo2", vo2);

        // 해당 게시글에 달린 댓글 목록 조회
        List<RecycleTipCommentVO> comments = commentService.selectByRecycleTipNum(recycleTip_num);
        log.info("comments.size(): {}", comments.size());
        model.addAttribute("comments", comments);

        return "community/recycleTip/selectOne";
    }

    @PostMapping("/community/recycleTip/insertOK")
    public String rt_insertOK(RecycleTipVO vo) throws IllegalStateException, IOException {
        log.info("/community/recycleTip/insertOK 요청");
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
            return "redirect:/community/recycleTip/selectAll";
        } else {
            return "redirect:/community/recycleTip/insert";
        }
    }

    @PostMapping("/community/recycleTip/updateOK")
    public String rt_updateOK(RecycleTipVO vo) throws IllegalStateException, IOException {
        log.info("/community/recycleTip/updateOK 요청");
        log.info("vo: {}", vo);

        log.info("파일 저장 경로: {}", realPath);

        MultipartFile file = vo.getFile();
        String originName = file.getOriginalFilename();
        log.info("originName: {}", originName);

        if (originName != null && !originName.isEmpty()) {
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
        // 이미지 변경이 없을 경우 기존 이미지 유지

        int result = service.updateOK(vo);
        log.info("result: {}", result);
        if (result == 1) {
            return "redirect:/community/recycleTip/selectOne?recycleTip_num=" + vo.getRecycleTip_num();
        } else {
            return "redirect:/community/recycleTip/update?recycleTip_num=" + vo.getRecycleTip_num();
        }
    }

    @PostMapping("/community/recycleTip/deleteOK")
    public String rt_deleteOK(@RequestParam("recycleTip_num") int recycleTip_num, RedirectAttributes redirectAttributes) {
        log.info("/community/recycleTip/deleteOK 요청");
        log.info("recycleTip_num: {}", recycleTip_num);

        RecycleTipVO vo = new RecycleTipVO();
        vo.setRecycleTip_num(recycleTip_num);

        int result = service.deleteOK(vo);
        log.info("result: {}", result);
        if (result == 1) {
            return "redirect:/community/recycleTip/selectAll";
        } else {
            redirectAttributes.addFlashAttribute("msg", "삭제에 실패했습니다.");
            return "redirect:/community/recycleTip/delete?recycleTip_num=" + recycleTip_num;
        }
    }
}
