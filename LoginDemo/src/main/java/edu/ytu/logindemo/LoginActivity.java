package edu.ytu.logindemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    static Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        findViewById(R.id.agree_and_continue).setOnClickListener(view -> {
            if (toast != null)
                toast.cancel();
            String phone = ((TextView) LoginActivity.this.findViewById(R.id.phone_input)).getText().toString();
            if (phone.length() == 11) {
                toast = Toast.makeText(getApplicationContext(), "正在登录，" + phone, Toast.LENGTH_SHORT);
                toast.show();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                LoginActivity.this.startActivity(intent);
            } else {
                toast = Toast.makeText(getApplicationContext(), "请输入正确的手机号！", Toast.LENGTH_LONG);
                toast.show();
            }
        });
    }
}