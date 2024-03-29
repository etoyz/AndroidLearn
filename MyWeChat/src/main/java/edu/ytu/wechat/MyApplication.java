package edu.ytu.wechat;

import android.app.Application;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDelegate;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyApplication extends Application {
    private static Toast toast;
    private static SharedPreferences credentials;
    private static SharedPreferences preferenceTheme;
    private static Map<String, String> tmpUsers = new HashMap<>(); // 模拟数据库，存储用户账号

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        reloadTheme();
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        // 获取本地暂存的数据
        credentials = this.getSharedPreferences("CREDENTIALS", Context.MODE_PRIVATE);
        preferenceTheme = this.getSharedPreferences("THEME", Context.MODE_PRIVATE);
        // 临时创建两个可以登录的账号
        tmpUsers.put("11111111111", "111111");
        tmpUsers.put("22222222222", "222222");
        //
        reloadTheme();
    }

    public Dialog showProgress(Context context, String text) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View progressView = inflater.inflate(R.layout.progress_spin, new RelativeLayout(context));
        TextView progressMessage = progressView.findViewById(R.id.custom_toast_message);
        progressMessage.setText(text);
        Dialog progressDialog = new AlertDialog.Builder(context).setView(progressView).setCancelable(false).create();
        progressDialog.show();
        Window window = progressDialog.getWindow();
        window.setLayout(550, 550);
        window.setBackgroundDrawable(getResources().getDrawable(R.drawable.empty)); //设置背景透明
        return progressDialog;
    }

    public AlertDialog showAlert(Context context, String text, int mode) {
        @LayoutRes int alertLayout = R.layout.toast_progress;
        switch (mode) {
            case 1:
                alertLayout = R.layout.toast_success;
                break;
            case 2:
                alertLayout = R.layout.toast_error;
                break;
            case 3:
                alertLayout = R.layout.toast_progress;
                break;
        }

        LayoutInflater inflater = LayoutInflater.from(context);
        View alertView = inflater.inflate(alertLayout, new RelativeLayout(context));
        TextView toastMessage = alertView.findViewById(R.id.custom_toast_message);
        toastMessage.setText(text);
        AlertDialog dialog = new AlertDialog.Builder(context).setView(alertView).setCancelable(true).create();

        dialog.show();
        Window window = dialog.getWindow();
        window.setLayout(550, 550);
        window.setBackgroundDrawable(getResources().getDrawable(R.drawable.empty)); //设置背景透明
        return dialog;
    }

    public AlertDialog showAlertWithCustomImage(Context context, String text, Drawable drawable) {
        // inflate view
        LayoutInflater inflater = LayoutInflater.from(context);
        View alertView = inflater.inflate(R.layout.toast_success, new RelativeLayout(context));
        // set param
        TextView toastMessage = alertView.findViewById(R.id.custom_toast_message);
        toastMessage.setText(text);
        ImageView toastImg = alertView.findViewById(R.id.custom_toast_image);
        toastImg.setImageDrawable(drawable);
        toastImg.setColorFilter(Color.WHITE);
        // show
        AlertDialog dialog = new AlertDialog.Builder(context).setView(alertView).setCancelable(true).create();
        dialog.show();
        // adjustment
        Window window = dialog.getWindow();
        window.setLayout(550, 550);
        window.setBackgroundDrawable(getResources().getDrawable(R.drawable.empty)); //设置背景透明
        return dialog;
    }

    @Deprecated
    public void showToast(Context context, String text, int duration, int mode) {
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
        toast.setDuration(duration);
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
        if (tmpUsers.containsKey(id) && tmpUsers.containsValue(password)) {
            credentials.edit().putString("id", id).putString("password", password).apply(); // 存储登录账号信息
            return true;
        } else
            return false;
    }

    public void setThemeDark(boolean dark_mode) {
        preferenceTheme.edit().putBoolean("dark_mode", dark_mode).apply();
    }

    public boolean isThemeDark() {
        return preferenceTheme.getBoolean("dark_mode", false);
    }

    public boolean isActivatedThemeDark() {
        // 若不是跟随系统, 返回用户设置的主题
        if (!isThemeFollowSystem()) {
            return isThemeDark();
        } else { // 若跟随系统, 返回系统主题
            boolean isDarkThemeOn =
                    (getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK) == Configuration.UI_MODE_NIGHT_YES; // 设备是否开启暗夜模式
            return isDarkThemeOn;
        }
    }

    public void setThemeFollowSystem(boolean follow_system) {
        preferenceTheme.edit().putBoolean("follow_system", follow_system).apply();
    }

    public boolean isThemeFollowSystem() {
        return preferenceTheme.getBoolean("follow_system", true);
    }

    public void reloadTheme() {
        AppCompatDelegate.setDefaultNightMode(
                isActivatedThemeDark() ?
                        AppCompatDelegate.MODE_NIGHT_YES : AppCompatDelegate.MODE_NIGHT_NO);
    }
}
