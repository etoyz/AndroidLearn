package edu.ytu.yangzhao;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

public class AAA extends AppCompatActivity {
    private TextView textView;
    private tView mTouch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aaa);
        textView = findViewById(R.id.aaa);
        mTouch = new tView(this);
        setContentView(mTouch);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        Log.i("XXXX", "按键代码" + keyCode);
//        Log.i("XXXX", "事件" + event.toString());
        switch (keyCode) {
            case KeyEvent.KEYCODE_VOLUME_UP:
                Log.i("XXXX", "你按下了音量+");
                textView.setText("你按下了音量+");
                break;
            case KeyEvent.KEYCODE_VOLUME_DOWN:
                Log.i("XXXX", "你按下了音量-");
                textView.setText("你按下了音量-");
                break;
            case KeyEvent.KEYCODE_BACK:
                Log.i("XXXX", "你按下了BACK");
                textView.setText("你按下了BACK");
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mTouch.x = event.getX();
                mTouch.y = event.getY();
                mTouch.postInvalidate();
                break;
            case MotionEvent.ACTION_UP:
                mTouch.x = event.getX();
                mTouch.y = event.getY();
                mTouch.postInvalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                mTouch.x = event.getX();
                mTouch.y = event.getY();
                mTouch.postInvalidate();
                break;
        }
        return true;
    }

    class tView extends View {
        private float x = 100;
        private float y = 100;
        Paint paint;

        public tView(Context context) {
            super(context);
            paint = new Paint();
        }

        public tView(Context context, @Nullable AttributeSet attrs) {
            super(context, attrs);
        }

        public tView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);
        }

        public tView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
            super(context, attrs, defStyleAttr, defStyleRes);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            paint.setColor(Color.BLUE);
//            canvas.drawColor(Color.RED);
            canvas.drawCircle(x, y, 50, paint);
        }
    }
}