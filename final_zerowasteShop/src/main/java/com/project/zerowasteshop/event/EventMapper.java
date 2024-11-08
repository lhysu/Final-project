package com.project.zerowasteshop.event;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EventMapper {

    // 단일 이벤트 조회
    public EventVO selectOne(EventVO vo);

    // 모든 이벤트 조회
    public List<EventVO> selectAll();

    // 페이징 처리를 위한 이벤트 조회
    public List<EventVO> selectAllPageBlock(int startRow, int pageBlock);

    // 검색어로 이벤트 조회
    public List<EventVO> searchList(String searchWord);

    // 검색 결과에 대한 페이징 처리
    public List<EventVO> searchListPageBlock(String searchWord, int startRow, int pageBlock);

    // 이벤트 추가
    public int insertOK(EventVO vo);

    // 이벤트 삭제
    public int deleteOK(EventVO vo);

    // 이벤트 업데이트
    public int updateOK(EventVO vo);

    // 전체 이벤트 수 가져오기
    public int getTotalRows();

    // 검색 결과의 총 행 수 가져오기
    public int getSearchTotalRows(String searchWord);
    
    // 조회수 증가 메서드
    public void incrementViewCount(int event_num);
}
