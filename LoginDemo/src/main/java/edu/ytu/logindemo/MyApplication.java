package edu.ytu.logindemo;

import android.app.Application;
import android.widget.Toast;

public class MyApplication extends Application {
    private static Toast toast;

    protected void showToast(String text, int duration) {
        if (toast != null)
            toast.cancel();
        toast = Toast.makeText(getApplicationContext(), text, duration);
        toast.show();
    }
}
