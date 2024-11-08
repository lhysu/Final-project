package com.project.zerowasteshop.recycletipcomment;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class RecycleTipCommentVO {
    private int tipComment_num;
    private int recycleTip_num;
    private String member_id;
    private String tipComment_content;
    private Timestamp tipComment_wdate;
}

