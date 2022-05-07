package edu.ytu.wechat.ui.home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import edu.ytu.wechat.R;
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

        binding.iconGrid.setNumColumns(4);
        binding.iconGrid.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return (position + 1) * 3; // TODO
            }

            @Override
            public Object getItem(int position) {
                return null;
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                ViewHolder holder;
                if (convertView == null) {
                    convertView = inflater.inflate(R.layout.fragment_icon_grid_item, parent, false);
                    holder = new ViewHolder();
                    holder.img = convertView.findViewById(R.id.grid_iv);
                    convertView.setTag(holder);
                } else {
                    holder = (ViewHolder) convertView.getTag();
                }
                // 通过 holder 修改 View
                holder.img.setImageResource(R.drawable.avatar);
                return convertView;
            }

            class ViewHolder {
                ImageView img;
            }
        });
        return binding.getRoot();
    }
}