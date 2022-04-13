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
    private static Map<String, String> tmpUsers = new HashMap<>(); // 模拟数据库，存储用户账号

    @Override
    public void onCreate() {
        super.onCreate();
        // 获取本地暂存的数据，用于验证登录状态
        credentials = this.getSharedPreferences("CREDENTIALS", Context.MODE_PRIVATE);
        // 临时创建两个可以登录的账号
        tmpUsers.put("11111111111", "111111");
        tmpUsers.put("22222222222", "222222");
    }

    public void showToast(String text, int duration) {
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

        // 验证登录状态
        if (tmpUsers.containsKey(id) && tmpUsers.containsValue(password))
            return true;
        else
            return false;
    }

    protected void logout() {
        credentials.edit().remove("id").remove("password").apply();
    }

    protected boolean login(String id, String password) {
        if (verifyMobileNumber(id)) { // 验证手机号格式
            showToast("正在登录，" + id, Toast.LENGTH_SHORT);
            // 模拟网络延迟
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (tmpUsers.containsKey(id) && tmpUsers.containsValue(password)) {
                credentials.edit().putString("id", id).putString("password", password).apply(); // 存储登录账号信息
                showToast("登录成功！", Toast.LENGTH_SHORT);
                return true;
            } else {
                showToast("密码错误！", Toast.LENGTH_SHORT);
                return false;
            }
        } else {
            showToast("手机号无效！", Toast.LENGTH_LONG);
            return false;
        }
    }
}
