package com.srd.wemate.model.dto.posts;

public class PostsSaveRequestDto {

    private String content;
    private String author;
    private boolean pin;
    private int gid;

    public PostsSaveRequestDto(String content, String author, boolean pin, int gid) {
        this.content = content;
        this.author = author;
        this.pin = pin;
        this.gid = gid;
    }

//    public Post toEntity() {
//        Post post = new Post(content, author, pin);
//        return post;
//    }

    public PostsSaveRequestDto() {
    }

    public String getContent() {
        return content;
    }

    public String getAuthor() {
        return author;
    }

    public boolean getPin() { return pin; }
}
