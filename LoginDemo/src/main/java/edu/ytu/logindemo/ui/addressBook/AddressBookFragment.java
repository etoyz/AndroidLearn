package edu.ytu.logindemo.ui.addressBook;

import android.graphics.Color;
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
import java.util.Collections;
import java.util.List;

import edu.ytu.logindemo.MyApplication;
import edu.ytu.logindemo.R;
import edu.ytu.logindemo.databinding.FragmentAddressBookBinding;

public class AddressBookFragment extends Fragment {
    private FragmentAddressBookBinding binding;
    private static MyApplication application;
    List<Friend> friendList;

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
        AddressBookAdapter adapter = new AddressBookAdapter(getContext(), friendList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                application.showAlert(getContext(), ((TextView) view.findViewById(R.id.name)).getText().toString(), 1);
            }
        });

        WaveSideBar sideBar = view.findViewById(R.id.side_bar);
        sideBar.setTextColor(Color.BLACK);
//        sideBar.setMaxOffset(100);
//        sideBar.setPosition(WaveSideBar.POSITION_RIGHT);
//        sideBar.setTextAlign(WaveSideBar.TEXT_ALIGN_CENTER);
        sideBar.setLazyRespond(false);
        sideBar.setOnSelectIndexItemListener(new WaveSideBar.OnSelectIndexItemListener() {
            @Override
            public void onSelectIndexItem(String index) {
                for (int i = 0; i < friendList.size(); i++) {
                    if (index.equalsIgnoreCase(friendList.get(i).getFirstLetter())) {
                        int positionForSelection = adapter.getPositionForSection(index);
                        listView.smoothScrollToPositionFromTop(positionForSelection, 0);
                    }
                }
            }

        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void init() {
        friendList = new ArrayList<>();
        friendList.add(new Friend(0, R.drawable.avatar, "张三"));
        friendList.add(new Friend(0, R.drawable.avatar, "李四"));
        friendList.add(new Friend(0, R.drawable.avatar, "王五"));
        friendList.add(new Friend(0, R.drawable.avatar, "杨召"));
        friendList.add(new Friend(0, R.drawable.avatar, "Tom"));
        friendList.add(new Friend(0, R.drawable.avatar, "Hellen"));
        friendList.add(new Friend(0, R.drawable.avatar, "Alexander"));
        friendList.add(new Friend(0, R.drawable.avatar, "Bob"));
        for (char i = 'A'; i < 'Z'; i++) {
            friendList.add(new Friend(0, R.drawable.avatar, i + "(Generated)"));
        }
        Collections.sort(friendList);
    }
}