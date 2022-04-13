package edu.ytu.logindemo.ui.find;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import edu.ytu.logindemo.MyApplication;
import edu.ytu.logindemo.R;

public class FindFragment extends Fragment {
    private static MyApplication application;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        application = (MyApplication) getActivity().getApplication();
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_find, container, false);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        view.findViewById(R.id.dp_btn).setOnClickListener(v -> {
            DatePickerDialog dialog = new DatePickerDialog(getContext());
            dialog.setTitle("请选择日期");
            dialog.show();
            dialog.setOnDateSetListener((view1, year, month, dayOfMonth) -> application.showToast(year + "年" + month + "月" + dayOfMonth + "日！", 1000));
        });

        view.findViewById(R.id.tp_btn).setOnClickListener(v -> {
            TimePickerDialog dialog = new TimePickerDialog(getContext(), (view12, hourOfDay, minute) -> {
                application.showToast(hourOfDay + "时" + minute + "分！", 1000);
//                Toast toast = new Toast(getContext());
//                toast.setView(LayoutInflater.from(v.getContext()).inflate(R.layout.toast_wechat, view.findViewById(android.R.id.content)));
//                toast.setText("123");
//                toast.show();
            }, 0, 0, true);
            dialog.setTitle("请选择时间");
            dialog.show();
        });

        view.findViewById(R.id.cm_btn).setOnClickListener(v -> {
            View cmDialogView = LayoutInflater.from(view.getContext()).inflate(R.layout.layout_chronometer, view.findViewById(android.R.id.content), false);
            AlertDialog dialog = new AlertDialog.Builder(view.getContext()).setView(cmDialogView).create();
            dialog.show();
        });
    }
}