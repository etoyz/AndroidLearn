package edu.ytu.wechat.ui.home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.google.android.material.tabs.TabLayoutMediator;

import edu.ytu.wechat.MyApplication;
import edu.ytu.wechat.databinding.ActivityChatBinding;

public class ChatActivity extends AppCompatActivity {
    private static MyApplication application;
    private static boolean isDrawerOpen = false;
    private static int position;
    ActivityChatBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        application = (MyApplication) getApplication();
        binding = ActivityChatBinding.inflate(getLayoutInflater());
        position = getIntent().getIntExtra("position", -1);
        setContentView(binding.getRoot());

        binding.title.setText(position + "");
        binding.iconBtn.setOnClickListener(v -> {
//                binding.editArea.scrollBy(0, 30); // 滑动效果

            alterBottomDrawer(0);
            binding.iconViewpager.setAdapter(new FragmentStateAdapter(getSupportFragmentManager(), getLifecycle()) {
                @NonNull
                @Override
                public Fragment createFragment(int position) {
                    Fragment fragment = new IconGridFragment();
                    Bundle args = new Bundle();
                    args.putInt("position", position);
                    fragment.setArguments(args);
                    return fragment;
                }

                @Override
                public int getItemCount() {
                    return 2;
                }
            });
            new TabLayoutMediator(binding.iconTabs, binding.iconViewpager,
                    (tab, position) -> {
                        if (position == 0) {
                            tab.setText("我的收藏");
                        } else if (position == 1) {
                            tab.setText("表情商店");
                        }
                    }
            ).attach();
//                binding.iconTabs.setupWithViewPager(binding.iconViewpager);
        });
        binding.back.setOnClickListener(v -> onBackPressed());

    }

    @Override
    public void onBackPressed() {
        if (isDrawerOpen) {
            alterBottomDrawer(2);
        } else {
            super.onBackPressed();
        }
    }

    /**
     * open or close the bottom drawer
     *
     * @param mode 0:auto 1:open 2:close
     */
    public void alterBottomDrawer(int mode) {
        if (mode == 0) // auto
            mode = isDrawerOpen ? 2 : 1;

        // 初始化输入区和表情抽屉的布局参数
        RelativeLayout.LayoutParams drawerParams = new RelativeLayout.LayoutParams(binding.bottomDrawer.getLayoutParams());
        RelativeLayout.LayoutParams editAreaParams = new RelativeLayout.LayoutParams(binding.editArea.getLayoutParams());
        // 调整输入区和表情抽屉的布局参数
        if (mode == 1) {
            editAreaParams.addRule(RelativeLayout.ABOVE, binding.bottomDrawer.getId());
            drawerParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
            binding.bottomDrawer.setVisibility(View.VISIBLE);
            isDrawerOpen = true;
        } else {
            editAreaParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
            binding.bottomDrawer.setVisibility(View.GONE);
            isDrawerOpen = false;
        }
        // 应用输入区和表情抽屉的布局参数
        binding.bottomDrawer.setLayoutParams(drawerParams);
        binding.editArea.setLayoutParams(editAreaParams);
    }
}