package edu.ytu.logindemo;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyApplication extends Application {
    private static Toast toast;
    private static SharedPreferences credentials;
    private static Map<String, String> tmpUsers = new HashMap<>();

    @Override
    public void onCreate() {
        super.onCreate();
        credentials = this.getSharedPreferences("CREDENTIALS", Context.MODE_PRIVATE);
        tmpUsers.put("11111111111", "111111");
        tmpUsers.put("22222222222", "222222");
    }

    protected void showToast(String text, int duration) {
        if (toast != null)
            toast.cancel();
        toast = Toast.makeText(getApplicationContext(), text, duration);
        toast.show();
    }

    protected boolean verifyMobileNumber(String s) {
        Pattern p = Pattern.compile("^[0-9]{11}$");
        Matcher matcher = p.matcher(s);
        return matcher.matches();
    }

    protected boolean checkLoginStatus() {
        String id = credentials.getString("id", "");
        String password = credentials.getString("password", "");

        // 调用接口验证
        if (tmpUsers.containsKey(id) && tmpUsers.containsValue(password))
            return true;
        else
            return false;
    }

    protected void logout() {
        credentials.edit().remove("id").remove("password").apply();
    }

    protected boolean login(String id, String password) {
        if (tmpUsers.containsKey(id) && tmpUsers.containsValue(password)) {
            credentials.edit().putString("id", id).putString("password", password).apply();
            return true;
        } else
            return false;
    }
}
