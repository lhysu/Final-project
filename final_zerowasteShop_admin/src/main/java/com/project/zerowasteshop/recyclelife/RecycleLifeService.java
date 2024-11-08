package com.project.zerowasteshop.recyclelife;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
public class RecycleLifeService {
	
	@Autowired
	RecycleLifeMapper mapper;

	public List<RecycleLifeVO> selectAll() {
		return mapper.selectAll();
	}

	public RecycleLifeVO selectOne(RecycleLifeVO vo) {
		return mapper.selectOne(vo);
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
	
	public List<RecycleLifeVO> selectTopViews() {	
		return mapper.selectTopViews();
	}
	
	public int toggleLike(int recycleLife_num, boolean isLiked) {
        if (isLiked == false) {
            mapper.increaseLikeCount(recycleLife_num);
        } else {
            mapper.decreaseLikeCount(recycleLife_num);
        }
        return mapper.getLikeCount(recycleLife_num); // 최종 좋아요 수 반환
    }

	public int getLikeCount(int recycleLife_num) {
		return mapper.getLikeCount(recycleLife_num);
	}

	public RecycleLifeVO getPost(int recycleLife_num) {
		return mapper.getPost(recycleLife_num);
	}

}

	

