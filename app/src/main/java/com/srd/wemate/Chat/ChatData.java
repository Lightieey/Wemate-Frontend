package com.srd.wemate.Chat;

public class ChatData {
    //이름
    private String i_id; //author
    private String u_id;
    //뷰타입(왼, 오, 중앙)
    private int viewType;
    private long u_img;//이미지의 타입은 나중에 확인해봐야 함
    private String time;
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getI_id() {
        return i_id;
    }

    public void setI_id(String i_id) {
        this.i_id = i_id;
    }

    public String getU_id() {
        return u_id;
    }

    public void setU_id(String u_id) {
        this.u_id = u_id;
    }

    public int getViewType() {
        return viewType;
    }

    public void setViewType(int viewType) {
        this.viewType = viewType;
    }

    public long getU_img() {
        return u_img;
    }

    public void setU_img(long u_img) {
        this.u_img = u_img;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public ChatData() {
    }


    public ChatData(String content, String i_id, int viewType) {
        this.content = content;
        this.viewType = viewType;
        this.i_id = i_id;
    }


}
