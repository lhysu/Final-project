package com.project.zerowasteshop.recycletipcomment;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RecycleTipCommentMapper {

    // 단일 댓글 조회
    RecycleTipCommentVO selectOne(RecycleTipCommentVO vo);

    // 모든 댓글 조회
    List<RecycleTipCommentVO> selectAll();

    // 특정 RecycleTip에 속한 모든 댓글 조회
    List<RecycleTipCommentVO> selectByRecycleTipNum(int recycleTip_num);

    // 댓글 등록
    int insertOK(RecycleTipCommentVO vo);

    // 댓글 삭제
    int deleteOK(RecycleTipCommentVO vo);

    // 댓글 업데이트
    int updateOK(RecycleTipCommentVO vo);

    // 페이징 처리를 위한 특정 게시글의 모든 댓글 조회 (recycleTip_num 추가)
    List<RecycleTipCommentVO> selectAllPageBlock(int recycleTip_num, int startRow, int pageBlock
    );

    // 전체 댓글 수 가져오기
    int getTotalRows();
}
