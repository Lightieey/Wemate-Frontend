package com.srd.wemate.model.dto.posts;


import com.srd.wemate.model.MateGroup;

public class PostsListResponseDto {

	private String content;
	private String author;
	private boolean pin;
	private Long id;
	private int gid;

	public Long getId() {
		return id;
	}

	public String getContent() {
		return content;
	}

	public String getAuthor() {
		return author;
	}

	public boolean isPin() {
		return pin;
	}

	public int getGid() {return gid;}

	@Override
	public String toString() {
		return "Posts{" +
				"id=" + id +
				", content=" + content +
				", author=" + author +
				", pin=" + pin +
				", gid=" + gid;
	}
}



