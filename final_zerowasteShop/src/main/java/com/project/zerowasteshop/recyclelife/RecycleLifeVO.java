package com.project.zerowasteshop.recyclelife;

import java.sql.Timestamp;

import org.springframework.web.multipart.MultipartFile;

public class RecycleLifeVO {
	
	private String member_id;
	private int recycleLife_num;
	private String recycleLife_title;
	private String recycleLife_content;
	private int recycleLife_views;
	private int recycleLife_likes;
	private String recycleLife_img;
	private Timestamp recycleLife_wdate;
	private MultipartFile file;
	
	@Override
	public String toString() {
		return "RecycleLifeVO [member_id=" + member_id + ", recycleLife_num=" + recycleLife_num + ", recycleLife_title="
				+ recycleLife_title + ", recycleLife_content=" + recycleLife_content + ", recycleLife_views="
				+ recycleLife_views + ", recycleLife_likes=" + recycleLife_likes + ", recycleLife_img="
				+ recycleLife_img + ", recycleLife_wdate=" + recycleLife_wdate + ", file=" + file + "]";
	}

	public String getMember_id() {
		return member_id;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}

	public int getRecycleLife_num() {
		return recycleLife_num;
	}

	public void setRecycleLife_num(int recycleLife_num) {
		this.recycleLife_num = recycleLife_num;
	}

	public String getRecycleLife_title() {
		return recycleLife_title;
	}

	public void setRecycleLife_title(String recycleLife_title) {
		this.recycleLife_title = recycleLife_title;
	}

	public String getRecycleLife_content() {
		return recycleLife_content;
	}

	public void setRecycleLife_content(String recycleLife_content) {
		this.recycleLife_content = recycleLife_content;
	}

	public int getRecycleLife_views() {
		return recycleLife_views;
	}

	public void setRecycleLife_views(int recycleLife_views) {
		this.recycleLife_views = recycleLife_views;
	}

	public int getRecycleLife_likes() {
		return recycleLife_likes;
	}

	public void setRecycleLife_likes(int recycleLife_likes) {
		this.recycleLife_likes = recycleLife_likes;
	}

	public String getRecycleLife_img() {
		return recycleLife_img;
	}

	public void setRecycleLife_img(String recycleLife_img) {
		this.recycleLife_img = recycleLife_img;
	}

	public Timestamp getRecycleLife_wdate() {
		return recycleLife_wdate;
	}

	public void setRecycleLife_wdate(Timestamp recycleLife_wdate) {
		this.recycleLife_wdate = recycleLife_wdate;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}
	

}
