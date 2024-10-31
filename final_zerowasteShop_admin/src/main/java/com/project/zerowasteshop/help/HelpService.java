package com.project.zerowasteshop.help;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class HelpService {

    @Autowired
    private HelpMapper mapper;

    // 문의 등록
    public int insertOK(HelpVO vo) {
        return mapper.insertOK(vo);
    }

    // 모든 문의 조회
    public List<HelpVO> selectAll() {
        return mapper.selectAll();
    }

    // 단일 문의 조회
    public HelpVO selectOne(HelpVO vo) {
        return mapper.selectOne(vo);
    }

    // 문의 업데이트
    public int updateOK(HelpVO vo) {
        return mapper.updateOK(vo);
    }

    // 문의 삭제
    public int deleteOK(HelpVO vo) {
        return mapper.deleteOK(vo);
    }

    // 문의 검색
    public List<HelpVO> searchList(String searchWord) {
        return mapper.searchList("%" + searchWord + "%");
    }

    // 페이징 처리를 위한 문의 조회
    public List<HelpVO> selectAllPageBlock(int cpage, int pageBlock) {
        int startRow = (cpage - 1) * pageBlock;
        return mapper.selectAllPageBlock(startRow, pageBlock);
    }

    // 전체 문의 수 가져오기
    public int getTotalRows() {
        return mapper.getTotalRows();
    }

    // 검색 결과에 대한 페이징 처리
    public List<HelpVO> searchListPageBlock(String searchWord, int cpage, int pageBlock) {
        int startRow = (cpage - 1) * pageBlock;
        return mapper.searchListPageBlock("%" + searchWord + "%", startRow, pageBlock);
    }

    // 검색 결과의 총 행 수 가져오기
    public int getSearchTotalRows(String searchWord) {
        return mapper.getSearchTotalRows("%" + searchWord + "%");
    }
    
    public void incrementViewCount(int help_num) {
        mapper.incrementViewCount(help_num);
    }
}
