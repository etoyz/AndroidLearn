package edu.ytu.wechat.ui.addressBook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import edu.ytu.wechat.R;

public class AddressBookAdapter extends BaseAdapter {
    private final Context context;
    private final List<Friend> friendList;

    public AddressBookAdapter(Context context, List<Friend> friendList) {
        this.context = context;
        this.friendList = friendList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) { // 不可复用
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.fragment_address_book_item, new FrameLayout(context));
            viewHolder.name = convertView.findViewById(R.id.name);
            viewHolder.avatar = convertView.findViewById(R.id.avatar);
            viewHolder.catalog = convertView.findViewById(R.id.catalog);
            convertView.setTag(viewHolder);
        } else { // 直接复用
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.avatar.setImageResource(friendList.get(position).getAvatar());
        viewHolder.name.setText(friendList.get(position).getName());
        String catalog = friendList.get(position).getFirstLetter();
        if (position == getPositionForSection(catalog)) {
            viewHolder.catalog.setVisibility(View.VISIBLE);
            viewHolder.catalog.setText(catalog);
        } else {
            viewHolder.catalog.setVisibility(View.GONE);
        }

        return convertView;
    }

    /**
     * 获取目录catalog首次出现位置
     */
    public int getPositionForSection(String catalog) {
        for (int i = 0; i < getCount(); i++) {
            String sortStr = friendList.get(i).getFirstLetter();
            if (catalog.equalsIgnoreCase(sortStr)) {
                return i;
            }
        }
        return -1;
    }

    static class ViewHolder {
        TextView catalog;
        ImageView avatar;
        TextView name;
    }

    @Override
    public int getCount() {
        return friendList.size();
    }

    @Override
    public Object getItem(int position) {
        return friendList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return friendList.get(position).getUid();
    }
}
