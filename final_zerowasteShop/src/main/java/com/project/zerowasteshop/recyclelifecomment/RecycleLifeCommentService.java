package com.project.zerowasteshop.recyclelifecomment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecycleLifeCommentService {
	
	@Autowired
	RecycleLifeCommentMapper mapper;

	public RecycleLifeCommentVO selectOne(RecycleLifeCommentVO vo) {
		return mapper.selectOne(vo);
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

	public List<RecycleLifeCommentVO> selectAll(int recycleLife_num) {
		return mapper.selectAll(recycleLife_num);
	}

	public List<RecycleLifeCommentVO> getCommentsByPost(int recycleLife_num) {
		return mapper.getCommentsByPost(recycleLife_num);
	}

}
