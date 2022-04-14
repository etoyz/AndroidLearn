package edu.ytu.logindemo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

public class SettingActivity extends AppCompatActivity {
    private static MyApplication application;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        application = (MyApplication) getApplication();
        setContentView(R.layout.activity_setting);

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
                    application.showToast(v.getContext(), "正在退出...", 99999, 3);
                    // delay
                    (new Handler()).postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            alertDialog.dismiss();
                            application.logout();
                            application.showToast(v.getContext(), "已退出！", 200, 1);
                            startActivity(new Intent(v.getContext(), LaunchActivity.class));
                        }
                    }, 1000);
                });
                alertDialog.show();
            }
        });
    }
}