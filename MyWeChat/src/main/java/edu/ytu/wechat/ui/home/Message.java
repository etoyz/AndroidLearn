package edu.ytu.wechat.ui.home;

import edu.ytu.wechat.ui.addressBook.Friend;

public class Message {
    private Friend friend;
    private String preview;
    private String details;

    public Message(Friend friend, String preview, String details) {
        this.friend = friend;
        this.preview = preview;
        this.details = details;
    }

    public Message() {
    }


    public Friend getFriend() {
        return friend;
    }

    public void setFriend(Friend friend) {
        this.friend = friend;
    }

    public String getPreview() {
        return preview;
    }

    public void setPreview(String preview) {
        this.preview = preview;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
