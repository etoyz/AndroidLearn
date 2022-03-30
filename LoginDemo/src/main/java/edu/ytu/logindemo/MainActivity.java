package edu.ytu.logindemo;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import edu.ytu.logindemo.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private static MyApplication application;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // 初始化
        super.onCreate(savedInstanceState);
        application = (MyApplication) getApplication();
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupWithNavController(binding.navView, navController);

//        navController.addOnDestinationChangedListener((navController1, navDestination, bundle) -> {
//            ((TextView) findViewById(R.id.title)).setText(navDestination.getLabel());
//            switch (navDestination.getId()){
//                case R.id.wechat_home:
//                    ((RecyclerView)findViewById(R.id.list1)).addOnItemTouchListener(new );
//                    break;
//                case R.id.address_book:
//                    Log.i("ii", "2");
//                    break;
//                case R.id.find:
//                    Log.i("ii", "3");
//                    break;
//                case R.id.profile:
//                    Log.i("ii", "4");
//                    break;
//            }
//        });
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}