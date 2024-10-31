package com.project.zerowasteshop.notice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
public class NoticeService {

    @Autowired
    private NoticeMapper mapper;


    public int insertOK(NoticeVO vo) {
        return mapper.insertOK(vo);
    }

    public List<NoticeVO> selectAll() {
        return mapper.selectAll();
    }

    public NoticeVO selectOne(NoticeVO vo) {
        return mapper.selectOne(vo);
    }

    public int updateOK(NoticeVO vo) {
        return mapper.updateOK(vo);
    }

    public int deleteOK(NoticeVO vo) {
        return mapper.deleteOK(vo);
    }

    public List<NoticeVO> searchList(String searchWord) {
        return mapper.searchList("%" + searchWord + "%");
    }

    public List<NoticeVO> selectAllPageBlock(int cpage, int pageBlock) {
        int startRow = (cpage - 1) * pageBlock;
        return mapper.selectAllPageBlock(startRow, pageBlock);
    }

    public List<NoticeVO> searchListPageBlock(String searchWord, int cpage, int pageBlock) {
        int startRow = (cpage - 1) * pageBlock;
        return mapper.searchListPageBlock("%" + searchWord + "%", startRow, pageBlock);
    }

    public int getTotalRows() {
        return mapper.getTotalRows();
    }

    public int getSearchTotalRows(String searchWord) {
        return mapper.getSearchTotalRows("%" + searchWord + "%");
    }
    
    public void incrementViewCount(int notice_num) {
        mapper.incrementViewCount(notice_num);
    }
}
