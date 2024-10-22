package com.project.zerowasteshop.notice;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface NoticeMapper {

	// 단일 공지사항 조회
    public NoticeVO selectOne(NoticeVO vo);
    
    // 모든 공지사항 조회
    public List<NoticeVO> selectAll();
    
    // 모든 공지사항 조회 페이징 처리
    public List<NoticeVO> selectAllPageBlock(int startRow, int pageBlock);
    
    // 검색어로 공지사항 조회
    public List<NoticeVO> searchList(String searchWord);
    
    // 검색어로 공지사항 조회 페이징 처리
    public List<NoticeVO> searchListPageBlock(String searchWord, int startRow, int pageBlock);

    public int insertOK(NoticeVO vo);

    public int deleteOK(NoticeVO vo);

    public int updateOK(NoticeVO vo);

    public int getTotalRows();

    public int getSearchTotalRows(String searchWord);
    
    // 조회수 증가 메서드
    public void incrementViewCount(int notice_num);
}
