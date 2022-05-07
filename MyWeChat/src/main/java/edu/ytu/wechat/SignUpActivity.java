package edu.ytu.wechat;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioGroup;

public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        RadioGroup group = findViewById(R.id.gender);
        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                Log.i("XXXX", checkedId == 1 ? "男" : "女");
            }
        });

        CheckBox checkBox = findViewById(R.id.agree_term);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.i("XXXX", isChecked ? "用户同意了条款" : "用户取消了同意条款");
            }
        });

        findViewById(R.id.reset_input).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                group.clearCheck();
            }
        });
    }
}