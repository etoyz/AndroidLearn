package edu.ytu.wechat.ui.home;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import edu.ytu.wechat.R;
import edu.ytu.wechat.placeholder.PlaceholderContent.PlaceholderItem;
import edu.ytu.wechat.databinding.FragmentMessageItemBinding;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link PlaceholderItem}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MessageRecyclerViewAdapter extends RecyclerView.Adapter<MessageRecyclerViewAdapter.ViewHolder> {

    private final List<PlaceholderItem> mValues;

    public MessageRecyclerViewAdapter(List<PlaceholderItem> items) {
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(FragmentMessageItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.avatar.setImageResource(R.drawable.avatar);
        holder.remark.setText(mValues.get(position).remark);
        holder.preview.setText(mValues.get(position).preview);
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final ImageView avatar;
        public final TextView remark;
        public final TextView preview;
        public PlaceholderItem mItem;

        public ViewHolder(FragmentMessageItemBinding binding) {
            super(binding.getRoot());
            avatar = binding.avatar;
            remark = binding.remark;
            preview = binding.preview;
        }

//        @Override
//        public String toString() {
//            return super.toString() + " '" + mContentView.getText() + "'";
//        }
    }
}