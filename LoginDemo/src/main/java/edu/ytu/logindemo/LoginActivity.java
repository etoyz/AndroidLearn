package edu.ytu.logindemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

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
            if (application.login(mobileNumber, password)) {
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
            }
        });
    }
}