package edu.ytu.logindemo;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.switchmaterial.SwitchMaterial;

public class SettingActivity extends AppCompatActivity {
    private static MyApplication application;

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        application = (MyApplication) getApplication();
        setTheme(application.getThemeRes());
        setContentView(R.layout.activity_setting);

        // 退出登录按钮
        findViewById(R.id.logout_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // alert dialog
                AlertDialog.Builder builder = new AlertDialog.Builder(SettingActivity.this);
                ViewGroup viewGroup = findViewById(android.R.id.content);
                View dialogView = LayoutInflater.from(SettingActivity.this).inflate(R.layout.dialog_logout, viewGroup, false);
                AlertDialog alertDialog = builder.setView(dialogView).create();
                // cancel event
                dialogView.findViewById(R.id.btn_cancel).setOnClickListener(v1 -> {
                    alertDialog.dismiss();
                });
                // confirm logout event
                dialogView.findViewById(R.id.btn_confirm).setOnClickListener(v1 -> {
                    application.showToast(v.getContext(), "正在退出...", Toast.LENGTH_LONG, 3);
                    // delay
                    (new Handler()).postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            alertDialog.dismiss();
                            application.logout();
                            application.showToast(v.getContext(), "已退出！", Toast.LENGTH_SHORT, 1);
                            startActivity(new Intent(v.getContext(), LaunchActivity.class));
                        }
                    }, 1000);
                });
                alertDialog.show();
            }
        });

        // 深色模式
        SwitchMaterial night_mode_switch = findViewById(R.id.night_mode);
        night_mode_switch.setChecked(application.getThemeRes() == R.style.Theme_Wechat_Night);
        night_mode_switch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (night_mode_switch.isChecked())
                    application.setTheme(R.style.Theme_Wechat_Night);
                else
                    application.setTheme(R.style.Theme_Wechat);
                recreate();
            }
        });

        findViewById(R.id.back).setOnClickListener(v -> finish());
    }
}