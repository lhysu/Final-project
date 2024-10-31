package com.project.zerowasteshop.recycletip;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RecycleTipService {

    @Autowired
    private RecycleTipMapper mapper;

    // Tip 등록
    public int insertOK(RecycleTipVO recycleTipVO) {
        return mapper.insertOK(recycleTipVO);
    }

    // Tip 업데이트
    public int updateOK(RecycleTipVO recycleTipVO) {
        return mapper.updateOK(recycleTipVO);
    }

    // Tip 삭제
    public int deleteOK(RecycleTipVO recycleTipVO) {
        return mapper.deleteOK(recycleTipVO);
    }

    // 단일 Tip 조회
    public RecycleTipVO selectOne(RecycleTipVO recycleTipVO) {
        return mapper.selectOne(recycleTipVO);
    }

    // 모든 Tip 조회
    public List<RecycleTipVO> selectAll() {
        return mapper.selectAll();
    }

    // Tip 검색
    public List<RecycleTipVO> searchList(String searchField, String searchWord) {
        String searchPattern = "%" + searchWord + "%";
        return mapper.searchList(searchField, searchPattern);
    }

    // 전체 Tip 수 가져오기
    public int getTotalRows() {
        return mapper.getTotalRows();
    }

    // 페이징 처리를 위한 Tip 조회
    public List<RecycleTipVO> selectAllPageBlock(int cpage, int pageBlock) {
        int startRow = (cpage - 1) * pageBlock;
        return mapper.selectAllPageBlock(startRow, pageBlock);
    }

    // 검색 결과의 총 행 수 가져오기 
    public int getSearchTotalRows(String searchField, String searchWord) {
        String searchPattern = "%" + searchWord + "%";
        return mapper.getSearchTotalRows(searchField, searchPattern);
    }

    // 검색 결과에 대한 페이징 처리 
    public List<RecycleTipVO> searchListPageBlock(String searchField, String searchWord, int cpage, int pageBlock) {
        int startRow = (cpage - 1) * pageBlock;
        String searchPattern = "%" + searchWord + "%";
        return mapper.searchListPageBlock(searchField, searchPattern, startRow, pageBlock);
    }

    // 조회수 증가
    public void incrementViewCount(int recycleTip_num) {
        mapper.incrementViewCount(recycleTip_num);
    }
}
