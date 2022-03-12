package edu.ytu.logindemo.ui.addressBook;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import edu.ytu.logindemo.databinding.FragmentAddressBookBinding;

public class AddressBookFragment extends Fragment {

    private FragmentAddressBookBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        AddressBookViewModel addressBookViewModel =
                new ViewModelProvider(this).get(AddressBookViewModel.class);

        binding = FragmentAddressBookBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textDashboard;
        addressBookViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}