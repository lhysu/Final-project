package com.project.zerowasteshop.recyclelifecomment;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class RecycleLifeCommentService {
	
	RecycleLifeCommentMapper mapper;

	public RecycleLifeCommentVO selectOne(RecycleLifeCommentVO vo) {
		return mapper.selectOne(vo);
	}

	public List<RecycleLifeCommentVO> selectAll() {
		return mapper.selectAll();
	}

	public int insertOK(RecycleLifeCommentVO vo) {
		return mapper.insertOK(vo);
	}

	public int deleteOK(RecycleLifeCommentVO vo) {
		return mapper.deleteOK(vo);
	}

	public int updateOK(RecycleLifeCommentVO vo) {
		return mapper.updateOK(vo);
	}

	public List<RecycleLifeCommentVO> selectAllPageBlock(int cpage, int pageBlock) {
		int startRow = (cpage - 1) * pageBlock;
        return mapper.selectAllPageBlock(startRow, pageBlock);
	}

	public int getTotalRows() {
		return mapper.getTotalRows();
	}

}
