package com.project.zerowasteshop.donateitem;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DonateItemMapper {

	public List<DonateItemVO> selectAll();

	public List<DonateItemVO> selectAllPageBlock(int startRow, int pageBlock);

	public int getTotalRows();

	public DonateItemVO selectOne(DonateItemVO vo);

	public List<DonateItemVO> searchListId(String searchWord);

	public List<DonateItemVO> searchListItem(String searchWord);

	public List<DonateItemVO> searchListPageBlockId(String searchWord, int startRow, int pageBlock);

	public List<DonateItemVO> searchListPageBlockItem(String searchWord, int startRow, int pageBlock);

	public int getSearchTotalRowsId(String searchWord);

	public int getSearchTotalRowsItem(String searchWord);

}
