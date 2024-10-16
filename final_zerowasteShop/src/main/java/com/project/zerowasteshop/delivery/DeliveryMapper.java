package com.project.zerowasteshop.delivery;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DeliveryMapper {

	DeliveryVO selectOne(DeliveryVO vo);

	List<DeliveryVO> selectAll();

	int getTotalRows();

	List<DeliveryVO> selectAllPageBlock(int startRow, int pageBlock);

	int getSearchTotalRows();

	List<DeliveryVO> searchListPageBlock();

	List<DeliveryVO> searchListProduct_name(String string);

	List<DeliveryVO> searchListTracking_num(String string);

	int getSearchTotalRowsProduct_name(String string);

	int getSearchTotalRowsTracking_num(String string);

	List<DeliveryVO> searchListPageBlockProduct_name(String string, int startRow, int endRow);

	List<DeliveryVO> searchListPageBlockTracking_num(String string, int startRow, int endRow);
	

}
