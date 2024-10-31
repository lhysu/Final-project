package com.project.zerowasteshop.help;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/help")
public class HelpController {

    @Autowired
    private HelpService service;

    // application.properties에서 설정한 변수(file.dir)를 DI
    @Value("${file.dir}")
    private String realPath;

    // h_insert() : String
    @GetMapping("/insert")
    public String h_insert() {
        log.info("/help/insert");
        return "help/insert";
    }

    // h_update(HelpVO, Model) : String
    @GetMapping("/update")
    public String h_update(HelpVO vo, Model model) {
        log.info("/help/update");
        log.info("vo:{}", vo);

        HelpVO vo2 = service.selectOne(vo);
        log.info("vo2:{}", vo2);

        model.addAttribute("vo2", vo2);

        return "help/update";
    }

    // h_delete() : String
    @GetMapping("/delete")
    public String h_delete() {
        log.info("/help/delete");
        return "help/delete";
    }

    // h_selectOne(HelpVO, Model) : String
    @GetMapping("/selectOne")
    public String h_selectOne(HelpVO vo, Model model) {
        log.info("/help/selectOne");
        log.info("vo:{}", vo);

        HelpVO vo2 = service.selectOne(vo);
        log.info("vo2:{}", vo2);

        if (vo2 != null) {
            // 조회수 증가 메서드 호출 (필요하다면 서비스에 구현)
            service.incrementViewCount(vo2.getHelp_num());
        }

        model.addAttribute("vo2", vo2);

        return "help/selectOne";
    }

    // h_selectAll(Model) : String
    @GetMapping("/selectAll")
    public String h_selectAll(Model model) {
        log.info("/help/selectAll");

        List<HelpVO> list = service.selectAll();
        log.info("list.size():{}", list.size());

        model.addAttribute("list", list);

        return "help/selectAll";
    }

    // h_selectAllPageBlock(Model, int, int) : String
    @GetMapping("/selectAllPageBlock")
    public String h_selectAllPageBlock(Model model,
                                       @RequestParam(defaultValue = "1") int cpage,
                                       @RequestParam(defaultValue = "5") int pageBlock) {
        log.info("/help/selectAllPageBlock");
        log.info("cpage:{}", cpage);
        log.info("pageBlock:{}", pageBlock);

        List<HelpVO> list = service.selectAllPageBlock(cpage, pageBlock);
        log.info("list.size():{}", list.size());

        model.addAttribute("list", list);

        // 총 페이지 수 계산
        int total_rows = service.getTotalRows();
        log.info("total_rows:{}", total_rows);
        int totalPageCount = 0;

        if (total_rows % pageBlock == 0) {
            totalPageCount = total_rows / pageBlock;
        } else {
            totalPageCount = total_rows / pageBlock + 1;
        }
        log.info("totalPageCount:{}", totalPageCount);

        model.addAttribute("totalPageCount", totalPageCount);
        model.addAttribute("cpage", cpage);

        return "help/selectAllPageBlock";
    }

    // h_searchList(Model, String, String) : String
    @GetMapping("/searchList")
    public String h_searchList(Model model,
                               @RequestParam(defaultValue = "") String searchWord,
                               @RequestParam(defaultValue = "1") int cpage,
                               @RequestParam(defaultValue = "5") int pageBlock) {
        log.info("/help/searchList");
        log.info("searchWord:{}", searchWord);
        log.info("cpage:{}", cpage);
        log.info("pageBlock:{}", pageBlock);

        List<HelpVO> list = service.searchListPageBlock(searchWord, cpage, pageBlock);
        log.info("list.size():{}", list.size());

        model.addAttribute("list", list);

        int total_rows = service.getSearchTotalRows(searchWord);
        log.info("total_rows:{}", total_rows);
        int totalPageCount = 0;

        if (total_rows % pageBlock == 0) {
            totalPageCount = total_rows / pageBlock;
        } else {
            totalPageCount = total_rows / pageBlock + 1;
        }
        log.info("totalPageCount:{}", totalPageCount);

        model.addAttribute("totalPageCount", totalPageCount);
        model.addAttribute("cpage", cpage);
        model.addAttribute("searchWord", searchWord);

        return "help/searchList";
    }

    // h_insertOK(HelpVO) : String
    @PostMapping("/insertOK")
    public String h_insertOK(HelpVO vo) throws IllegalStateException, IOException {
        log.info("/help/insertOK");
        log.info("vo:{}", vo);

        log.info("realPath:{}", realPath);

        MultipartFile file = vo.getFile();
        String originName = file.getOriginalFilename();
        log.info("originName:{}", originName);

        if (originName == null || originName.isEmpty()) {
            vo.setHelp_img("default.png");
        } else {
            String saveName = "img_" + System.currentTimeMillis()
                    + originName.substring(originName.lastIndexOf("."));
            log.info("saveName:{}", saveName);
            vo.setHelp_img(saveName);

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
            return "redirect:/help/selectAll";
        } else {
            return "redirect:/help/insert";
        }
    }

    // h_deleteOK(HelpVO) : String
    @PostMapping("/deleteOK")
    public String h_deleteOK(HelpVO vo) {
        log.info("/help/deleteOK");
        log.info("vo:{}", vo);

        int result = service.deleteOK(vo);
        log.info("result:{}", result);
        if (result == 1) {
            return "redirect:/help/selectAll";
        } else {
            return "redirect:/help/delete?help_num=" + vo.getHelp_num();
        }
    }

    // h_updateOK(HelpVO) : String
    @PostMapping("/updateOK")
    public String h_updateOK(HelpVO vo) throws IllegalStateException, IOException {
        log.info("/help/updateOK");
        log.info("vo:{}", vo);

        log.info("realPath:{}", realPath);

        MultipartFile file = vo.getFile();
        String originName = file.getOriginalFilename();
        log.info("originName:{}", originName);

        if (originName == null || originName.isEmpty()) {
            vo.setHelp_img(vo.getHelp_img());
        } else {
            String saveName = "img_" + System.currentTimeMillis()
                    + originName.substring(originName.lastIndexOf("."));
            log.info("saveName:{}", saveName);
            vo.setHelp_img(saveName);

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
            return "redirect:/help/selectOne?help_num=" + vo.getHelp_num();
        } else {
            return "redirect:/help/update?help_num=" + vo.getHelp_num();
        }
    }
}