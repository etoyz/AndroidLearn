package edu.ytu.logindemo;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    private static MyApplication application;
    private View toolbar;

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
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}