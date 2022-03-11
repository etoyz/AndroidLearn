package edu.ytu.logindemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    private static MyApplication application;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        application = (MyApplication) getApplication();
        setContentView(R.layout.activity_login);
        findViewById(R.id.agree_and_continue).setOnClickListener(view -> {
            String mobileNumber = ((TextView) LoginActivity.this.findViewById(R.id.phone_input)).getText().toString();
            String password = ((TextView) LoginActivity.this.findViewById(R.id.password_input)).getText().toString();
            if (application.verifyMobileNumber(mobileNumber)) {
                application.showToast("正在登录，" + mobileNumber, Toast.LENGTH_SHORT);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (application.login(mobileNumber, password)) {
                    application.showToast("欢迎回来！", Toast.LENGTH_SHORT);
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    LoginActivity.this.startActivity(intent);
                } else
                    application.showToast("密码错误！", Toast.LENGTH_SHORT);
            } else {
                application.showToast("请输入正确的手机号！", Toast.LENGTH_LONG);
            }
        });
    }
}