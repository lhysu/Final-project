package com.project.zerowasteshop.order;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.project.zerowasteshop.product.mapper.ProductMapper;
import com.project.zerowasteshop.product.model.ProductVO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BestsellerService {

    @Autowired
    private OrderMapper orderMapper;
    
    @Autowired
    private ProductMapper productMapper;

    /**
     * 전체 판매량을 기준으로 상위 N개의 베스트셀러 상품을 반환합니다.
     * 캐싱을 사용하여 성능을 최적화합니다.
     * @param limit 반환할 베스트셀러 수
     * @return 베스트셀러 상품 리스트
     */
    @Cacheable(value = "bestsellers", key = "#limit", unless = "#result == null || #result.isEmpty()")
    public List<ProductVO> getBestsellers(int limit) {
        List<BestsellerDTO> bestsellerDTOs = orderMapper.findTopSellingProducts(limit);
        log.info("Retrieved {} bestsellers from OrderMapper", bestsellerDTOs.size());

        List<ProductVO> bestsellers = bestsellerDTOs.stream()
            .map(dto -> productMapper.findProductByNum(dto.getProduct_num().longValue()))
            .filter(Objects::nonNull)
            .collect(Collectors.toList());

        log.info("Mapped {} bestsellers to ProductVO entities", bestsellers.size());

        return bestsellers;
    }
}