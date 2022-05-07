package edu.ytu.wechat;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    private static MyApplication application;
    private View toolbar;

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // 初始化
        super.onCreate(savedInstanceState);
        application = (MyApplication) getApplication();
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);

        NavHostFragment navHostFragment =
                (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment_activity_main);
        assert navHostFragment != null;
        NavController navController = navHostFragment.getNavController();
        NavigationUI.setupWithNavController((NavigationBarView) findViewById(R.id.nav_view), navController);

        navController.addOnDestinationChangedListener((navController1, navDestination, bundle) -> {
            ((TextView) findViewById(R.id.title)).setText(navDestination.getLabel());
            toolbar.setVisibility(View.VISIBLE);
            switch (navDestination.getId()) {
                case R.id.wechat_home:
//                    ((RecyclerView) findViewById(R.id.list1)).addOnItemTouchListener(new);
                    break;
                case R.id.address_book:
                    Log.i("XXXX", "2");
                    break;
                case R.id.find:
                    Log.i("XXXX", "3");
                    break;
                case R.id.profile:
                    Log.i("XXXX", "4");
                    toolbar.setVisibility(View.GONE);
                    break;
            }
        });

        // 右上角加号
        findViewById(R.id.moreBtn).setOnClickListener(v -> {
            PopupMenu menu = new PopupMenu(MainActivity.this, v);
            menu.setForceShowIcon(true);
            menu.inflate(R.menu.more_menu);
            menu.show();
            menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    application.showAlertWithCustomImage(MainActivity.this, item.toString(), item.getIcon());
                    return true;
                }
            });
        });
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}