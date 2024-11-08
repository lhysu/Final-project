package com.project.zerowasteshop.recycletipcomment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecycleTipCommentService {
	
	@Autowired
	private RecycleTipCommentMapper mapper;

	// 단일 댓글 조회
	public RecycleTipCommentVO selectOne(RecycleTipCommentVO vo) {
		return mapper.selectOne(vo);
	}

	// 모든 댓글 조회
	public List<RecycleTipCommentVO> selectAll() {
		return mapper.selectAll();
	}

	// 특정 RecycleTip에 속한 모든 댓글 조회
	public List<RecycleTipCommentVO> selectByRecycleTipNum(int recycleTip_num) {
		return mapper.selectByRecycleTipNum(recycleTip_num);
	}

	// 댓글 등록
	public int insertOK(RecycleTipCommentVO vo) {
		return mapper.insertOK(vo);
	}

	// 댓글 삭제
	public int deleteOK(RecycleTipCommentVO vo) {
		return mapper.deleteOK(vo);
	}

	// 댓글 업데이트
	public int updateOK(RecycleTipCommentVO vo) {
		return mapper.updateOK(vo);
	}

    // 페이징 처리를 위한 특정 게시글의 모든 댓글 조회
    public List<RecycleTipCommentVO> selectAllPageBlock(int recycleTip_num, int cpage, int pageBlock) {
        int startRow = (cpage - 1) * pageBlock;
        return mapper.selectAllPageBlock(recycleTip_num, startRow, pageBlock);
    }

	// 전체 댓글 수 가져오기
	public int getTotalRows() {
		return mapper.getTotalRows();
	}
}
