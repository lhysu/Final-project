package com.project.zerowasteshop.recyclelifecomment;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class RecycleLifeCommentVO {

	private int lifeComment_num;
	private int recycleLife_num;
	private String member_id;
	private String lifeComment_content;
	private Timestamp lifeComment_wdate;
	
}
