package edu.ytu.wechat.ui.home;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import edu.ytu.wechat.R;
import edu.ytu.wechat.databinding.FragmentMessageItemBinding;

import java.util.List;

public class MessageRecyclerViewAdapter extends RecyclerView.Adapter<MessageRecyclerViewAdapter.ViewHolder> {

    private final List<Message> messageList;

    public MessageRecyclerViewAdapter(List<Message> list) {
        messageList = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(FragmentMessageItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.setMessage(messageList.get(position));
    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public void setMessage(Message message) {
            this.message = message;
            avatar.setImageResource(message.getFriend().getAvatar());
            name.setText(message.getFriend().getName());
            preview.setText(message.getPreview());
        }

        public Message message;
        private final ImageView avatar;
        private final TextView name;
        private final TextView preview;

        public ViewHolder(FragmentMessageItemBinding binding) {
            super(binding.getRoot());
            avatar = binding.avatar;
            name = binding.name;
            preview = binding.preview;
        }
    }
}