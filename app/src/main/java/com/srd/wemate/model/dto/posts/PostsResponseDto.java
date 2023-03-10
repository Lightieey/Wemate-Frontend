package com.srd.wemate.model.dto.posts;

public class PostsResponseDto {

    private Long id;
    private String content;
    private String author;
    private boolean pin;

    public int getGid() {
        return gid;
    }

    private int gid;

//    public PostsResponseDto(Post entity) {
//        this.id = entity.getId();
//        this.content = entity.getContent();
//        this.author = entity.getAuthor();
//        this.pin = entity.isPin();
//    }

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
}
