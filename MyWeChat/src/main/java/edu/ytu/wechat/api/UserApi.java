package edu.ytu.wechat.api;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import edu.ytu.wechat.R;
import edu.ytu.wechat.ui.addressBook.Friend;
import edu.ytu.wechat.ui.home.Message;

public class UserApi {
    private static int uid;

    private static List<Message> messageList = new ArrayList<>();

    private static List<Friend> friendList = new ArrayList<>();


    public static List<Message> retrieveMessageList() {
        retrieveFriendList();
        for (int i = 0; i < friendList.size(); i++) {
            messageList.add(new Message(friendList.get(i), "消息预览", "321"));
        }

        return messageList;
    }

    public static List<Friend> retrieveFriendList() {
        friendList.add(new Friend(0, R.drawable.avatar, "张三"));
        friendList.add(new Friend(0, R.drawable.avatar, "李四"));
        friendList.add(new Friend(0, R.drawable.avatar, "王五"));
        friendList.add(new Friend(0, R.drawable.avatar, "杨召"));
        friendList.add(new Friend(0, R.drawable.avatar, "Tom"));
        friendList.add(new Friend(0, R.drawable.avatar, "Hellen"));
        friendList.add(new Friend(0, R.drawable.avatar, "Alexander"));
        friendList.add(new Friend(0, R.drawable.avatar, "Bob"));
        for (char i = 'A'; i < 'X'; i++) {
            friendList.add(new Friend(0, R.drawable.avatar, i + "(Generated)"));
        }
        Collections.sort(friendList);

        return friendList;
    }
}
