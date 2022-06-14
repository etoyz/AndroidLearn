package edu.ytu.wechat.ui.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
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
                // 此ViewHolder如果是回收来的，”yours“可能之前被设置为不可见
                yours.setVisibility(View.VISIBLE);
                other.setVisibility(View.GONE);
                if (chatMessage.getIconView() != null) {
                    iconYours.setImageDrawable(chatMessage.getIconView().getDrawable());
                    iconYours.setVisibility(View.VISIBLE);
                    msgYours.setVisibility(View.GONE);
                } else {
                    msgYours.setText(chatMessage.getContent());
                    msgYours.setVisibility(View.VISIBLE);
                    iconYours.setVisibility(View.GONE);
                }
            } else {
                // 此ViewHolder如果是回收来的，“other”可能之前被设置为不可见
                other.setVisibility(View.VISIBLE);
                yours.setVisibility(View.GONE);
                if (chatMessage.getIconView() != null) {
                    iconOther.setImageDrawable(chatMessage.getIconView().getDrawable());
                    iconOther.setVisibility(View.VISIBLE);
                    msgOther.setVisibility(View.GONE);
                } else {
                    msgOther.setText(chatMessage.getContent());
                    msgOther.setVisibility(View.VISIBLE);
                    iconOther.setVisibility(View.GONE);
                }
            }
        }

        public ChatMessage chatMessage;
        private final ConstraintLayout yours;
        private final ConstraintLayout other;
        private final TextView msgOther;
        private final TextView msgYours;
        private final ImageView avatarYours;
        private final ImageView avatarOther;
        private final ImageView iconYours;
        private final ImageView iconOther;

        public ViewHolder(ActivityChatItemBinding binding) {
            super(binding.getRoot());
            msgOther = binding.msgOther;
            msgYours = binding.msgYours;
            avatarYours = binding.avatarYours;
            avatarOther = binding.avatarOther;
            iconYours = binding.iconYours;
            iconOther = binding.iconOther;
            yours = binding.yours;
            other = binding.other;
        }
    }
}
