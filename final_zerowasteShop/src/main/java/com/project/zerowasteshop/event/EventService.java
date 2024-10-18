package com.project.zerowasteshop.event;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EventService {

    @Autowired
    private EventMapper mapper;

    // 이벤트 추가
    public int insertOK(EventVO vo) {
        return mapper.insertOK(vo);
    }

    // 모든 이벤트 조회
    public List<EventVO> selectAll() {
        return mapper.selectAll();
    }

    // 단일 이벤트 조회
    public EventVO selectOne(EventVO vo) {
        return mapper.selectOne(vo);
    }

    // 이벤트 업데이트
    public int updateOK(EventVO vo) {
        return mapper.updateOK(vo);
    }

    // 이벤트 삭제
    public int deleteOK(EventVO vo) {
        return mapper.deleteOK(vo);
    }

    // 이벤트 검색
    public List<EventVO> searchList(String searchWord) {
        return mapper.searchList("%" + searchWord + "%");
    }

    // 페이징 처리를 위한 이벤트 조회
    public List<EventVO> selectAllPageBlock(int cpage, int pageBlock) {
        int startRow = (cpage - 1) * pageBlock;
        return mapper.selectAllPageBlock(startRow, pageBlock);
    }

    // 전체 이벤트 수 가져오기
    public int getTotalRows() {
        return mapper.getTotalRows();
    }

    // 검색 결과에 대한 페이징 처리
    public List<EventVO> searchListPageBlock(String searchWord, int cpage, int pageBlock) {
        int startRow = (cpage - 1) * pageBlock;
        return mapper.searchListPageBlock("%" + searchWord + "%", startRow, pageBlock);
    }

    // 검색 결과의 총 행 수 가져오기
    public int getSearchTotalRows(String searchWord) {
        return mapper.getSearchTotalRows("%" + searchWord + "%");
    }
    
    public void incrementViewCount(int event_num) {
        mapper.incrementViewCount(event_num);
    }
    
}
