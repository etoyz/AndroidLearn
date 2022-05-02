package edu.ytu.logindemo.ui.addressBook;

import androidx.annotation.DrawableRes;

public class Friend {
    int avatar;
    String name;

    public Friend(@DrawableRes int avatar, String name) {
        this.avatar = avatar;
        this.name = name;
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
}