package com.srd.wemate.model.dto.posts;

public class PostsUpdateRequestDto {

    private String content;
    private boolean pin;

    public PostsUpdateRequestDto(String content, boolean pin) {
        this.content = content;
        this.pin = pin;
    }

    public PostsUpdateRequestDto() {
    }

    public String getContent() {
        return content;
    }

    public boolean isPin() {
        return pin;
    }
}