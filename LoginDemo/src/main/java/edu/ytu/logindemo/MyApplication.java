package edu.ytu.logindemo;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.LayoutRes;

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

    public void showToast(Context context, String text, long duration, int mode) {
        if (toast != null)
            toast.cancel();
        toast = new Toast(context);
        @LayoutRes int toastRes = R.layout.toast_progress;
        switch (mode) {
            case 1:
                toastRes = R.layout.toast_success;
                break;
            case 2:
                toastRes = R.layout.toast_error;
                break;
            case 3:
                toastRes = R.layout.toast_progress;
                break;
        }
        View toastView = LayoutInflater.from(context).inflate(toastRes, new RelativeLayout(context));
        TextView toastMessage = toastView.findViewById(R.id.custom_toast_message);
        toastMessage.setText(text);
        toast.setView(toastView);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
        (new Handler()).postDelayed(new Runnable() {
            @Override
            public void run() {
                toast.cancel();
            }
        }, duration);
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
        if (tmpUsers.containsKey(id) && tmpUsers.containsValue(password)) {
            credentials.edit().putString("id", id).putString("password", password).apply(); // 存储登录账号信息
            return true;
        } else
            return false;
    }
}
