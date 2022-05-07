package edu.ytu.wechat;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class ServiceActivity extends AppCompatActivity {
    private static MyApplication application;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        application = (MyApplication) getApplication();
        setContentView(R.layout.activity_services);

        findViewById(R.id.back).setOnClickListener(v -> finish());
    }
}