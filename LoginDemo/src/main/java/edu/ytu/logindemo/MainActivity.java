package edu.ytu.logindemo;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import edu.ytu.logindemo.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private static MyApplication application;
    private ActivityMainBinding binding;
    private View toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // 初始化
        super.onCreate(savedInstanceState);
        application = (MyApplication) getApplication();
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        toolbar = findViewById(R.id.toolbar);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupWithNavController(binding.navView, navController);

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