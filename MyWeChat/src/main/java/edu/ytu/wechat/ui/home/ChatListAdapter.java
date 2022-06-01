package edu.ytu.wechat.ui.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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

    /**
     * 新增ChatMessage
     *
     * @param message 新增的message
     * @return 新增message的position
     */
    public int addChatMessage(ChatMessage message) {
        chatMessageList.add(message);
        return chatMessageList.size() - 1;
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
            if (chatMessage.getContent().equals(""))
                chatMessage.setContent("      ");
            if (chatMessage.isYours()) {
                msgYours.setText(chatMessage.getContent());
                // 此ViewHolder如果是回收来的，”msgYours“可能之前被设置为不可见
                msgYours.setVisibility(View.VISIBLE);
                avatarYours.setVisibility(View.VISIBLE);
                msgOther.setVisibility(View.GONE);
                avatarOther.setVisibility(View.GONE);
            } else {
                msgOther.setText(chatMessage.getContent());
                // 此ViewHolder如果是回收来的，“msgOther”可能之前被设置为不可见
                msgOther.setVisibility(View.VISIBLE);
                avatarOther.setVisibility(View.VISIBLE);
                msgYours.setVisibility(View.GONE);
                avatarYours.setVisibility(View.GONE);
            }
        }

        public ChatMessage chatMessage;
        private final TextView msgOther;
        private final TextView msgYours;
        private final ImageView avatarYours;
        private final ImageView avatarOther;

        public ViewHolder(ActivityChatItemBinding binding) {
            super(binding.getRoot());
            msgOther = binding.msgOther;
            msgYours = binding.msgYours;
            avatarYours = binding.avatarYours;
            avatarOther = binding.avatarOther;
        }
    }
}
