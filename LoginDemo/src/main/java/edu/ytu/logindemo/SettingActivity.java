package edu.ytu.logindemo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
                AlertDialog.Builder builder = new AlertDialog.Builder(SettingActivity.this);
                ViewGroup viewGroup = findViewById(android.R.id.content);
                View dialogView = LayoutInflater.from(SettingActivity.this).inflate(R.layout.dialog_logout, viewGroup, false);
                AlertDialog dialog = builder.setView(dialogView).create();
                dialogView.findViewById(R.id.btn_cancel).setOnClickListener(v1 -> {
                    dialog.dismiss();
                });
                dialogView.findViewById(R.id.btn_confirm).setOnClickListener(v1 -> {
                    dialog.dismiss();
                    application.logout();
                    startActivity(new Intent(v.getContext(), LaunchActivity.class));
                });
                dialog.show();
            }
        });
    }
}