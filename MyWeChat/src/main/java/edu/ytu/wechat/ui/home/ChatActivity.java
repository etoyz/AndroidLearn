package edu.ytu.wechat.ui.home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.RelativeLayout;

import com.google.android.material.tabs.TabLayoutMediator;

import edu.ytu.wechat.MyApplication;
import edu.ytu.wechat.api.UserApi;
import edu.ytu.wechat.databinding.ActivityChatBinding;

public class ChatActivity extends AppCompatActivity {
    private static MyApplication application;
    private static boolean isDrawerOpen = false;
    private static boolean isKeyBoardOpen = false;
    private static int position;
    ActivityChatBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        application = (MyApplication) getApplication();
        binding = ActivityChatBinding.inflate(getLayoutInflater());
        position = getIntent().getIntExtra("position", -1);
        setContentView(binding.getRoot());

        binding.title.setText(UserApi.retrieveMessageList().get(position).getFriend().getName());
        binding.iconBtn.setOnClickListener(v -> {
            alterBottomDrawer(0);
//                binding.editArea.scrollBy(0, 30); // 滑动效果
//                binding.iconTabs.setupWithViewPager(binding.iconViewpager);
        });
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
        binding.back.setOnClickListener(v -> onBackPressed());
        // 监听软键盘事件
        SoftKeyBoardListener.setListener(this, new SoftKeyBoardListener.OnSoftKeyBoardChangeListener() {
            @Override
            public void keyBoardShow(int height) {
                alterBottomDrawer(2);
                isKeyBoardOpen = true;
            }

            @Override
            public void keyBoardHide(int height) {
                isKeyBoardOpen = false;
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (isDrawerOpen) {
            alterBottomDrawer(2);
        } else if (isKeyBoardOpen) {
            closeIME();
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
        if (mode == 1) { // 打开
            editAreaParams.addRule(RelativeLayout.ABOVE, binding.bottomDrawer.getId());
            drawerParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
            binding.bottomDrawer.setVisibility(View.VISIBLE);
            isDrawerOpen = true;
            // 关闭输入法
            closeIME();
        } else {
            editAreaParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
            binding.bottomDrawer.setVisibility(View.GONE);
            isDrawerOpen = false;
        }
        // 应用输入区和表情抽屉的布局参数
        binding.bottomDrawer.setLayoutParams(drawerParams);
        binding.editArea.setLayoutParams(editAreaParams);
    }

    /**
     * close IME
     */
    public void closeIME() {
        InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        manager.hideSoftInputFromWindow(binding.getRoot().getWindowToken(), 0);
    }

    static class SoftKeyBoardListener {
        private View rootView;//activity的根视图
        int rootViewVisibleHeight;//纪录根视图的显示高度
        private OnSoftKeyBoardChangeListener onSoftKeyBoardChangeListener;

        public SoftKeyBoardListener(Activity activity) {
            //获取activity的根视图
            rootView = activity.getWindow().getDecorView();

            //监听视图树中全局布局发生改变或者视图树中的某个视图的可视状态发生改变
            rootView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    //获取当前根视图在屏幕上显示的大小
                    Rect r = new Rect();
                    rootView.getWindowVisibleDisplayFrame(r);

                    int visibleHeight = r.height();
                    if (rootViewVisibleHeight == 0) {
                        rootViewVisibleHeight = visibleHeight;
                        return;
                    }

                    //根视图显示高度没有变化，可以看作软键盘显示／隐藏状态没有改变
                    if (rootViewVisibleHeight == visibleHeight) {
                        return;
                    }

                    //根视图显示高度变小超过200，可以看作软键盘显示了
                    if (rootViewVisibleHeight - visibleHeight > 200) {
                        if (onSoftKeyBoardChangeListener != null) {
                            onSoftKeyBoardChangeListener.keyBoardShow(rootViewVisibleHeight - visibleHeight);
                        }
                        rootViewVisibleHeight = visibleHeight;
                        return;
                    }

                    //根视图显示高度变大超过200，可以看作软键盘隐藏了
                    if (visibleHeight - rootViewVisibleHeight > 200) {
                        if (onSoftKeyBoardChangeListener != null) {
                            onSoftKeyBoardChangeListener.keyBoardHide(visibleHeight - rootViewVisibleHeight);
                        }
                        rootViewVisibleHeight = visibleHeight;
                        return;
                    }

                }
            });
        }

        private void setOnSoftKeyBoardChangeListener(OnSoftKeyBoardChangeListener onSoftKeyBoardChangeListener) {
            this.onSoftKeyBoardChangeListener = onSoftKeyBoardChangeListener;
        }

        public interface OnSoftKeyBoardChangeListener {
            void keyBoardShow(int height);

            void keyBoardHide(int height);
        }

        public static void setListener(Activity activity, OnSoftKeyBoardChangeListener onSoftKeyBoardChangeListener) {
            SoftKeyBoardListener softKeyBoardListener = new SoftKeyBoardListener(activity);
            softKeyBoardListener.setOnSoftKeyBoardChangeListener(onSoftKeyBoardChangeListener);
        }
    }
}