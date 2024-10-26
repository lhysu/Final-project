package com.project.zerowasteshop.recyclelife;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/community/recycleLife")
public class RecycleLifeRestController {
	
	@Autowired
    private RecycleLifeService service;
	
	@PostMapping("/community/recycleLife/{recycleLife_num}/like")
    public ResponseEntity<Integer> toggleLike(@PathVariable int recycleLife_num) {
        // 현재 좋아요 상태를 토글하는 로직
        int updatedLikeCount = service.toggleLike(recycleLife_num);
        log.info("toggleLike 호출");
        return ResponseEntity.ok(updatedLikeCount);  // 변경된 좋아요 수를 반환
    }

}
