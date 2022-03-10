package edu.ytu.yangzhao;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("TT", "creat");
    }

    public void generate_random_num(View view) {
        TextView t = findViewById(R.id.random_num);
        t.setText(String.valueOf((new Random()).nextInt(100)));
    }
}