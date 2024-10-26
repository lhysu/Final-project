package com.project.zerowasteshop.help;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface HelpMapper {
	// 단일 문의 조회
    public HelpVO selectOne(HelpVO helpVO);

    // 모든 문의 조회
    public List<HelpVO> selectAll();

    // 페이징 처리를 위한 문의 조회
    public List<HelpVO> selectAllPageBlock(int startRow, int pageBlock);

    // 검색어로 문의 조회
    public List<HelpVO> searchList(String searchWord);

    // 검색 결과에 대한 페이징 처리
    public List<HelpVO> searchListPageBlock(String searchWord, int startRow, int pageBlock);

    // 문의 등록
    public int insertOK(HelpVO helpVO);

    // 문의 삭제
    public int deleteOK(HelpVO helpVO);

    // 문의 업데이트
    public int updateOK(HelpVO helpVO);

    // 전체 문의 수 가져오기
    public int getTotalRows();

    // 검색 결과의 총 행 수 가져오기
    public int getSearchTotalRows(String searchWord);
    
    public void incrementViewCount(int help_num);
}
