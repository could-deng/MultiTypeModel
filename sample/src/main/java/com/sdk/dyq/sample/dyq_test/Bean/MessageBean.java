package com.sdk.dyq.sample.dyq_test.Bean;

/**
 * Created by yuanqiang on 2017/5/9.
 * RecyclerViewTestAdapter的item的bean类
 */

public class MessageBean {
    private int id;//id==0,代表用户自己
    private String name;
    private String msgContent;
    private String addTime;

    public MessageBean() {
    }

    public MessageBean(int id, String name, String msgContent, String addTime) {
        this.id = id;
        this.name = name;
        this.msgContent = msgContent;
        this.addTime = addTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMsgContent() {
        return msgContent;
    }

    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent;
    }

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }
}
