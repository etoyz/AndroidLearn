package edu.ytu.logindemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class LaunchActivity extends AppCompatActivity {
    private static MyApplication application;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        application = (MyApplication) getApplication();
        setContentView(R.layout.activity_launch);
        findViewById(R.id.login_btn).setOnClickListener(view -> {
            startActivity(new Intent(LaunchActivity.this, LoginActivity.class));
        });

        if (application.checkLoginStatus()) {
            application.showToast("欢迎回来！", Toast.LENGTH_SHORT);
            this.startActivity(new Intent(this, MainActivity.class));
        }
    }

}