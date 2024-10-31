package com.project.zerowasteshop.recycletip;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RecycleTipMapper {

    // Tip 등록
    public int insertOK(RecycleTipVO recycleTipVO);

    // Tip 삭제
    public int deleteOK(RecycleTipVO recycleTipVO);

    // Tip 업데이트
    public int updateOK(RecycleTipVO recycleTipVO);

    // 단일 Tip 조회
    public RecycleTipVO selectOne(RecycleTipVO recycleTipVO);

    // 모든 Tip 조회
    public List<RecycleTipVO> selectAll();

    // 검색어로 Tip 조회
    public List<RecycleTipVO> searchList(String searchField, String searchWord);
    
    public List<RecycleTipVO> searchListPageBlock(String searchField, String searchWord, int startRow, int pageBlock);

    // 전체 Tip 수 가져오기
    public int getTotalRows();

    // 페이징 처리를 위한 Tip 조회
    public List<RecycleTipVO> selectAllPageBlock(int startRow, int pageBlock);

    // 검색 결과의 총 행 수 가져오기
    public int getSearchTotalRows(String searchField, String searchWord);

    // 조회수 증가 메서드
    public void incrementViewCount(int recycleTip_num);
}
