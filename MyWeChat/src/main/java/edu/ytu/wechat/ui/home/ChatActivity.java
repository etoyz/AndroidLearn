package edu.ytu.wechat.ui.home;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import edu.ytu.wechat.MyApplication;
import edu.ytu.wechat.databinding.ActivityChatBinding;

public class ChatActivity extends AppCompatActivity {
    private static MyApplication application;
    private static boolean isDrawerOpen = false;

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
//                binding.editArea.scrollBy(0, 30); // 滑动效果

                // 初始化输入区和表情抽屉的布局参数
                RelativeLayout.LayoutParams drawerParams = new RelativeLayout.LayoutParams(binding.bottomDrawer.getLayoutParams());
                RelativeLayout.LayoutParams editAreaParams = new RelativeLayout.LayoutParams(binding.editArea.getLayoutParams());
                if (!isDrawerOpen) { // 抽屉未打开
                    editAreaParams.addRule(RelativeLayout.ABOVE, binding.bottomDrawer.getId());
                    drawerParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
                    binding.bottomDrawer.setVisibility(View.VISIBLE);
                    isDrawerOpen = true;
                } else {
                    editAreaParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
                    binding.bottomDrawer.setVisibility(View.GONE);
                    isDrawerOpen = false;
                }
                binding.bottomDrawer.setLayoutParams(drawerParams);
                binding.editArea.setLayoutParams(editAreaParams);
            }
        });


    }
}