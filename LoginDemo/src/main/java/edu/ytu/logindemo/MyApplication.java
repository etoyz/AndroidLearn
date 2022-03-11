package edu.ytu.logindemo;

import android.app.Application;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyApplication extends Application {
    private static Toast toast;

    protected void showToast(String text, int duration) {
        if (toast != null)
            toast.cancel();
        toast = Toast.makeText(getApplicationContext(), text, duration);
        toast.show();
    }

    protected boolean verifyMobileNumber(String s) {
        Pattern p = Pattern.compile("^[0-9]{11}$");
        Matcher matcher = p.matcher(s);
        return matcher.matches();
    }
}
