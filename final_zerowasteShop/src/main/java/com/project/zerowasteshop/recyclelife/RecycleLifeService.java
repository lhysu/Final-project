package com.project.zerowasteshop.recyclelife;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecycleLifeService {
	
	@Autowired
	RecycleLifeMapper mapper;

	public List<RecycleLifeVO> selectAll() {
		return mapper.selectAll();
	}

	public RecycleLifeVO rl_selectOne(RecycleLifeVO vo) {
		return mapper.rl_selectOne(vo);
	}

	public int insertOK(RecycleLifeVO vo) {
		return mapper.insertOK(vo);
	}

	public int updateOK(RecycleLifeVO vo) {
		return mapper.updateOK(vo);
	}

	public int deleteOK(RecycleLifeVO vo) {
		return mapper.deleteOK(vo);
	}

	public List<RecycleLifeVO> selectAllPageBlock(int cpage, int pageBlock) {
		int startRow = (cpage - 1) * pageBlock;
        return mapper.selectAllPageBlock(startRow, pageBlock);
	}

	public int getTotalRows() {
		return mapper.getTotalRows();
	}

	public List<RecycleLifeVO> searchList(String searchKey, String searchWord) {
		return mapper.searchList("%" + searchWord + "%");
	}

	public void increaseViews(int recycleLife_num) {
		mapper.increaseViews(recycleLife_num);
		
	}

	public int toggleLike(int recycleLife_num) {
        int currentLikes = mapper.getLikes(recycleLife_num);
        if (currentLikes % 2 == 0) {
            mapper.incrementLikeCount(recycleLife_num);  // 짝수일 때 증가
        } else {
            mapper.decrementLikeCount(recycleLife_num);  // 홀수일 때 감소
        }
        return mapper.getLikes(recycleLife_num); // 변경된 좋아요 수 반환
	}

}

	

