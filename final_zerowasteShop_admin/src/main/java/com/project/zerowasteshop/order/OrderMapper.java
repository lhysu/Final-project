package com.project.zerowasteshop.order;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper {

	List<OrderJoinProductVO> selectAllPageBlock(int startRow, int pageBlock);

	int getTotalRows();

	List<OrderVO> searchListPageBlockId(String searchWord, int startRow, int pageBlock);

	List<OrderVO> searchListPageBlockPname(String searchWord, int startRow, int pageBlock);

	int getSearchTotalRowsId(String searchWord);

	int getSearchTotalRowsPname(String searchWord);

	OrderVO selectOneOrder(String merchant_uid);

	List<OrderItemVO> selectOneItem(String merchantUid);
	
    /**
     * 전체 판매량을 기준으로 상위 N개의 베스트셀러 상품을 조회
     */
    List<BestsellerDTO> findTopSellingProducts(int limit);


}
