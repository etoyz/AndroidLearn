package edu.ytu.wechat;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.TextView;

import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {
    private static MyApplication application;
    private TextView mobileNumberInput;
    private TextView passwordInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // 初始化
        super.onCreate(savedInstanceState);
        application = (MyApplication) getApplication();
        setContentView(R.layout.activity_login);
        mobileNumberInput = findViewById(R.id.phone_input);
        passwordInput = findViewById(R.id.password_input);
        // 监听“同意并继续“按钮
        findViewById(R.id.agree_and_continue).setOnClickListener(view -> {
            String mobileNumber = mobileNumberInput.getText().toString();
            String password = passwordInput.getText().toString();
            // 登录应用流程
            if (application.verifyMobileNumber(mobileNumber)) { // 验证手机号格式
                Dialog progress = application.showProgress(this, "正在登录，" + mobileNumber);

                new Thread(new Runnable() { // 此线程向后台请求登录
                    @Override
                    public void run() {
                        SystemClock.sleep(1000); // 模拟网络延迟
                        runOnUiThread(new Runnable() { // 登录成功后，在UI线程更改页面
                            @Override
                            public void run() {
                                progress.dismiss();
                                if (application.login(mobileNumber, password)) {
                                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                } else {
                                    application.showAlert(LoginActivity.this, "密码错误！", 2);
                                }
                            }
                        });
                    }
                }).start();
            } else {
                application.showAlert(this, "手机号无效！", 2);
            }
        });
        // 密码只允许输入数字和大小写字母
        passwordInput.addTextChangedListener(new TextWatcher() {
            boolean illegal = false;
            int flag;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Pattern p = Pattern.compile("[0-9a-zA-Z]"); // 密码框中允许输入的字符

                if (s.length() > 0)
                    // 匹配最后输入的字符，若不合法，则弹出提示，并标记illegal和flag变量用于修正
                    if (!p.matcher(s.subSequence(s.length() - 1, s.length())).matches()) {
                        illegal = true;
                        flag = s.length() - 1;
                        application.showAlert(LoginActivity.this, "只允许输入数字和大小写字母！！", 2);
                    }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (illegal) {
                    illegal = false;
                    s.delete(flag, flag + 1); // 删除不合法字符
                }
            }
        });
    }
}