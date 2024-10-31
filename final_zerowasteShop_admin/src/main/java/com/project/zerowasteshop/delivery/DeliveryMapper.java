package com.project.zerowasteshop.delivery;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DeliveryMapper {

	public DeliveryVO selectOne(DeliveryVO vo);

	public List<DeliveryVO> selectAll();

	public int getTotalRows();

	public List<DeliveryVO> selectAllPageBlock(int startRow, int pageBlock);

	public int getSearchTotalRows();

	public List<DeliveryVO> searchListPageBlock();

	public List<DeliveryVO> searchListDelivery_num(String string);

	public List<DeliveryVO> searchListTracking_num(String string);

	public int getSearchTotalRowsProduct_name(String string);

	public int getSearchTotalRowsTracking_num(String string);

	public List<DeliveryVO> searchListPageBlockProduct_name(String string, int startRow, int endRow);

	public List<DeliveryVO> searchListPageBlockTracking_num(String string, int startRow, int endRow);

	public void saveDelivery(DeliveryVO deliveryInfo);
	

}
