package edu.ytu.logindemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {
    private static MyApplication application;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // 初始化
        super.onCreate(savedInstanceState);
        application = (MyApplication) getApplication();
        setContentView(R.layout.activity_login);
        // 监听“同意并继续“按钮
        findViewById(R.id.agree_and_continue).setOnClickListener(view -> {
            String mobileNumber = ((TextView) LoginActivity.this.findViewById(R.id.phone_input)).getText().toString();
            String password = ((TextView) LoginActivity.this.findViewById(R.id.password_input)).getText().toString();
            // 登录应用流程
            if (application.login(mobileNumber, password)) {
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
            }
        });
    }
}