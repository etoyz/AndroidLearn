package edu.ytu.logindemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class LaunchActivity extends AppCompatActivity {
    private static MyApplication application;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // 初始化
        super.onCreate(savedInstanceState);
        application = (MyApplication) getApplication();
        setContentView(R.layout.activity_launch);
        // 监听登录按钮
        findViewById(R.id.login_btn).setOnClickListener(view -> {
            startActivity(new Intent(LaunchActivity.this, LoginActivity.class));
        });
        findViewById(R.id.signup_btn).setOnClickListener(view -> {
            startActivity(new Intent(LaunchActivity.this, SignUpActivity.class));
        });

        // 若检测到之前登录过，则直接进入首页
        if (application.checkLoginStatus()) {
            application.showToast(LaunchActivity.this, "欢迎回来！", 500);
            this.startActivity(new Intent(this, MainActivity.class));
        }
    }

}