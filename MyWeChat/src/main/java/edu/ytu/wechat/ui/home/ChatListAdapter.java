package edu.ytu.wechat.ui.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import edu.ytu.wechat.databinding.ActivityChatItemBinding;

public class ChatListAdapter extends RecyclerView.Adapter<ChatListAdapter.ViewHolder> {
    List<ChatMessage> chatMessageList;

    public ChatListAdapter(List<ChatMessage> chatMessageList) {
        this.chatMessageList = chatMessageList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ActivityChatItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setChatMessage(chatMessageList.get(position));
    }

    @Override
    public int getItemCount() {
        return chatMessageList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public void setChatMessage(ChatMessage chatMessage) {
            this.chatMessage = chatMessage;
            if (chatMessage.isYours()) {
                msgYours.setText(chatMessage.getContent());
                msgOther.setVisibility(View.GONE);
            } else {
                msgOther.setText(chatMessage.getContent());
                msgYours.setVisibility(View.GONE);
            }
        }

        public ChatMessage chatMessage;
        private final TextView msgOther;
        private final TextView msgYours;

        public ViewHolder(ActivityChatItemBinding binding) {
            super(binding.getRoot());
            msgOther = binding.msgOther;
            msgYours = binding.msgYours;
        }
    }
}