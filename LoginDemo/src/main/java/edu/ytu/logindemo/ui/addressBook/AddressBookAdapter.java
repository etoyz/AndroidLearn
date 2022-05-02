package edu.ytu.logindemo.ui.addressBook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import edu.ytu.logindemo.R;

public class AddressBookAdapter extends BaseAdapter {
    private final Context context;
    private final List<Friend> friendList;

    public AddressBookAdapter(Context context, List<Friend> friendList) {
        this.context = context;
        this.friendList = friendList;
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
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.fragment_address_book_item, null);
            viewHolder.name = convertView.findViewById(R.id.name);
            viewHolder.avatar = convertView.findViewById(R.id.avatar);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.avatar.setImageResource(friendList.get(position).getAvatar());
        viewHolder.name.setText(friendList.get(position).getName());

        return convertView;
    }

    static class ViewHolder {
        ImageView avatar;
        TextView name;
    }
}
