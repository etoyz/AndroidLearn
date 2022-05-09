package edu.ytu.wechat.ui.home;

import java.util.Date;

import edu.ytu.wechat.ui.addressBook.Friend;

public class ChatMessage {
    private String content;

    private Friend owner;

    private Date time;

    private boolean isYours;

    public ChatMessage(String content, Friend owner, Date time, boolean isYours) {
        this.content = content;
        this.owner = owner;
        this.time = time;
        this.isYours = isYours;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Friend getOwner() {
        return owner;
    }

    public void setOwner(Friend owner) {
        this.owner = owner;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public boolean isYours() {
        return isYours;
    }

    public void setYours(boolean yours) {
        isYours = yours;
    }
}
