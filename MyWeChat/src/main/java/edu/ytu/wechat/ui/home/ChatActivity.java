package edu.ytu.wechat.ui.home;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import edu.ytu.wechat.MyApplication;
import edu.ytu.wechat.databinding.ActivityChatBinding;

public class ChatActivity extends AppCompatActivity {
    private static MyApplication application;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        application = (MyApplication) getApplication();
        ActivityChatBinding binding = ActivityChatBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        int position = getIntent().getIntExtra("position", -1);
        binding.title.setText(position + "");
        binding.iconBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                binding.editArea.scrollBy(0, 30);
            }
        });


    }
}