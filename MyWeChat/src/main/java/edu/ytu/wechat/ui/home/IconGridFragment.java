package edu.ytu.wechat.ui.home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.ytu.wechat.databinding.FragmentIconGridBinding;

public class IconGridFragment extends Fragment {
    private int position;
    FragmentIconGridBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            position = getArguments().getInt("position");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentIconGridBinding.inflate(inflater, container, false);
        binding.title.setText("Tab" + position);
        return binding.getRoot();
    }
}