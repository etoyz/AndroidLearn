package edu.ytu.logindemo;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.switchmaterial.SwitchMaterial;

public class SettingActivity extends AppCompatActivity {
    private static MyApplication application;

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        application = (MyApplication) getApplication();
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
                    alertDialog.dismiss();
                    Dialog progressDialog = application.showProgress(v.getContext(), "正在退出...");
                    // delay
                    (new Handler()).postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            progressDialog.dismiss();
                            application.logout();
                            startActivity(new Intent(v.getContext(), LaunchActivity.class));
                        }
                    }, 1000);
                });
                alertDialog.show();
            }
        });

        // 深色模式
        SwitchMaterial night_mode_follow_sys_switch = findViewById(R.id.night_mode_follow_sys);
        SwitchMaterial night_mode_switch = findViewById(R.id.night_mode);
        night_mode_follow_sys_switch.setChecked(application.isThemeFollowSystem());
        night_mode_switch.setChecked(application.isThemeDark());
        night_mode_switch.setEnabled(!application.isThemeFollowSystem());

        night_mode_follow_sys_switch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (night_mode_follow_sys_switch.isChecked()) {
                    application.setThemeFollowSystem(true);
                    night_mode_switch.setEnabled(false);
                } else {
                    application.setThemeFollowSystem(false);
                    night_mode_switch.setEnabled(true);
                }
                application.reloadTheme();
            }
        });
        night_mode_switch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (night_mode_switch.isChecked()) {
                    application.setThemeDark(true);
                } else {
                    application.setThemeDark(false);
                }
                application.reloadTheme();
            }
        });

        findViewById(R.id.back).setOnClickListener(v -> finish());
    }
}