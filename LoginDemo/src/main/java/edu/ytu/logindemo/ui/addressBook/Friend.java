package edu.ytu.logindemo.ui.addressBook;

import androidx.annotation.DrawableRes;

public class Friend implements Comparable<Friend> {
    private int uid;
    private int avatar; // 头像
    private String name; // 名字

    private String pinyin; // 姓名对应的拼音
    private String firstLetter; // 拼音的首字母

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public String getFirstLetter() {
        return firstLetter;
    }

    public void setFirstLetter(String firstLetter) {
        this.firstLetter = firstLetter;
    }

    public Friend(int uid, @DrawableRes int avatar, String name) {
        this.avatar = avatar;
        this.name = name;
        //根据姓名获取拼音
        pinyin = Cn2Spell.getPinYin(name);
        //拼音首字母
        firstLetter = Cn2Spell.getPinYinFirstLetter(name).toUpperCase();
        if (!firstLetter.matches("[A-Z]")) { // 如果不在A-Z中则默认为“#”
            firstLetter = "#";
        }
    }

    public int getAvatar() {
        return avatar;
    }

    public void setAvatar(@DrawableRes int avatar) {
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(Friend another) {
        if (firstLetter.equals("#") && !another.getFirstLetter().equals("#")) {
            return 1;//排序在后
        } else if (!firstLetter.equals("#") && another.getFirstLetter().equals("#")) {
            return -1;//排序在前
        } else {
            return pinyin.compareToIgnoreCase(another.getPinyin());
        }
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }
}