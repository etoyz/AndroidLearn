package edu.ytu.yangzhao;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import edu.ytu.yangzhao.databinding.ActivityIntentTestBinding;

public class IntentTestActivity extends AppCompatActivity {
    ActivityIntentTestBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_test);
        binding = ActivityIntentTestBinding.inflate(getLayoutInflater());


    }
}