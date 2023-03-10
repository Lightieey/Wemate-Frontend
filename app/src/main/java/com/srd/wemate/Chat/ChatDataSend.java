package com.srd.wemate.Chat;

public class ChatDataSend {

    private String i_id;
    private String content;


    public String getI_id() {
        return i_id;
    }

    public void setI_id(String i_id) {
        this.i_id = i_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ChatDataSend() {
    }


    public ChatDataSend(String i_id,String Content) {
        this.i_id = i_id;
        this.content = content;

    }

}
