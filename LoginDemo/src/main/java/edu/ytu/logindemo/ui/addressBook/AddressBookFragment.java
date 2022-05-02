package edu.ytu.logindemo.ui.addressBook;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

import edu.ytu.logindemo.MyApplication;
import edu.ytu.logindemo.R;
import edu.ytu.logindemo.databinding.FragmentAddressBookBinding;

public class AddressBookFragment extends Fragment {
    private FragmentAddressBookBinding binding;
    private static MyApplication application;
    List<Friend> findDates;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        application = (MyApplication) getActivity().getApplication();
        init();
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentAddressBookBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ListView listView = view.findViewById(R.id.friends);
        listView.setAdapter(new AddressBookAdapter(getContext(), findDates));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                application.showAlert(getContext(), ((TextView) view.findViewById(R.id.name)).getText().toString(), 1);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void init() {
        findDates = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            findDates.add(new Friend(R.drawable.avatar, "朋友" + i));
        }
    }
}