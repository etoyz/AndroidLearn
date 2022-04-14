package edu.ytu.logindemo.ui.find;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Chronometer;
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
        // 日期选择按钮
        view.findViewById(R.id.dp_btn).setOnClickListener(v -> {
            DatePickerDialog dialog = new DatePickerDialog(getContext());
            dialog.setTitle("请选择日期");
            dialog.show();
            dialog.setOnDateSetListener((view1, year, month, dayOfMonth) -> {
                application.showToast(v.getContext(), year + "年" + month + "月" + dayOfMonth + "日！", Toast.LENGTH_LONG, 1);
            });
        });

        // 时间选择按钮
        view.findViewById(R.id.tp_btn).setOnClickListener(v -> {
            TimePickerDialog dialog = new TimePickerDialog(getContext(), (view12, hourOfDay, minute) -> {
                application.showToast(v.getContext(), hourOfDay + "时" + minute + "分！", Toast.LENGTH_LONG, 1);
            }, 0, 0, true);
            dialog.setTitle("请选择时间");
            dialog.show();
        });

        // 秒表按钮
        view.findViewById(R.id.cm_btn).setOnClickListener(v -> {
            // 弹窗
            View cmDialogView = LayoutInflater.from(view.getContext()).inflate(R.layout.layout_chronometer, view.findViewById(android.R.id.content), false);
            AlertDialog dialog = new AlertDialog.Builder(view.getContext()).setView(cmDialogView).create();
            dialog.show();
            // 秒表组件
            Chronometer chronometer = cmDialogView.findViewById(R.id.cm);
            chronometer.setFormat("%s");
            // 秒表按钮事件
            class ChronometerEventsListener implements View.OnClickListener {
                private long tmp = 0;

                @Override
                public void onClick(View v) {
                    switch (v.getId()) {
                        case R.id.start:
                            if (tmp > 0) {
                                chronometer.setBase(chronometer.getBase() + (SystemClock.elapsedRealtime() - tmp));
                                chronometer.start();
                            } else {
                                chronometer.setBase(SystemClock.elapsedRealtime());
                                chronometer.start();
                            }
                            break;
                        case R.id.pause:
                            chronometer.stop();
                            tmp = SystemClock.elapsedRealtime();
                            break;
                        case R.id.restart:
                            chronometer.setBase(SystemClock.elapsedRealtime());
                            chronometer.start();
                            break;
                        case R.id.stop:
                            chronometer.stop();
                            tmp = 0;
                            break;
                    }
                }
            }
            View.OnClickListener listener = new ChronometerEventsListener();
            cmDialogView.findViewById(R.id.start).setOnClickListener(listener);
            cmDialogView.findViewById(R.id.pause).setOnClickListener(listener);
            cmDialogView.findViewById(R.id.stop).setOnClickListener(listener);
            cmDialogView.findViewById(R.id.restart).setOnClickListener(listener);
        });
    }
}