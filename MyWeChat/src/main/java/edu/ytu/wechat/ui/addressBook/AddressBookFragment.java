package edu.ytu.wechat.ui.addressBook;

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

import java.util.List;

import edu.ytu.wechat.MyApplication;
import edu.ytu.wechat.R;
import edu.ytu.wechat.api.UserApi;
import edu.ytu.wechat.databinding.FragmentAddressBookListBinding;

public class AddressBookFragment extends Fragment {
    private FragmentAddressBookListBinding binding;
    private static MyApplication application;
    List<Friend> friendList;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        application = (MyApplication) getActivity().getApplication();
        friendList = UserApi.retrieveFriendList();
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentAddressBookListBinding.inflate(inflater, container, false);
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
        sideBar.setLazyRespond(false);
        sideBar.setOnSelectIndexItemListener(new WaveSideBar.OnSelectIndexItemListener() {
            @Override
            public void onSelectIndexItem(String index) {
                int positionForSelection = adapter.getPositionForSection(index);
                if (positionForSelection != -1)
                    listView.smoothScrollToPositionFromTop(positionForSelection, 0);
            }

        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}