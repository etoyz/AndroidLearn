package edu.ytu.wechat.ui.home;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import edu.ytu.wechat.MyApplication;
import edu.ytu.wechat.R;

public class ChatActivity extends AppCompatActivity {
    private static MyApplication application;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        application = (MyApplication) getApplication();

        int position = getIntent().getIntExtra("position", -1);
        TextView titleView = findViewById(R.id.title);
        titleView.setText(position+"");
    }
}