package edu.ytu.yangzhao;

import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class ResourceActivity extends AppCompatActivity {
    private ImageView iv,level_iv,scale_iv,rotate_iv,clip_iv,animalist_iv,transition_iv,anim_selector;
    private TextView tv;
    private Switch sw;

    private boolean isChecked;
    int index=0;
    int level=0;
    boolean flag=true;
    int[] CHECKED=new int[]{android.R.attr.state_checked};
    int[]UNCHECKED=new int[]{};
    Button btn_shape;
    MediaPlayer mediaPlayer;
    Handler handler=new Handler(){

        public void handleMessage(Message msg){
            super.handleMessage(msg);
            if(index==6){
                index=0;
            }else{
                index++;
            }
            if (level<10000){
                level=level+1000;
            }else {
                level=0;
            }
            level_iv.setImageLevel(index);
            scale_iv.setImageLevel(level);
            rotate_iv.setImageLevel(level);
            clip_iv.setImageLevel(level);
            postDelayed(runnable,1000);
        }
    };
    Runnable runnable=new Runnable() {
        @Override
        public void run() {
            handler.sendEmptyMessage(0);
//            AnimatedVectorDrawable drawable= (AnimatedVectorDrawable) animalist_iv.getDrawable();
//            drawable.start();
//            btn_shape.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    TransitionDrawable drawable2=(TransitionDrawable)getDrawable(R.drawable.transition);
//                    drawable2.startTransition(2000);
//                }
//            });

        }
    };




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resouce);
        //iv=(ImageView)findViewById(R.id.img);
        //tv=(TextView)findViewById(R.id.tv);
        // sw=(Switch)findViewById(R.id.sw);
        level_iv=(ImageView)findViewById(R.id.levellist);
        btn_shape=(Button)findViewById(R.id.btn_shape);
        scale_iv=(ImageView)findViewById(R.id.scale);
        rotate_iv=(ImageView)findViewById(R.id.rotate);
        clip_iv=(ImageView)findViewById(R.id.clip);
        animalist_iv=(ImageView)findViewById(R.id.anim_list);
        transition_iv=(ImageView)findViewById(R.id.transition);
        //anim_selector=(ImageView)findViewById(R.id.anim_selector);
        handler.sendEmptyMessage(0);


    }
}
