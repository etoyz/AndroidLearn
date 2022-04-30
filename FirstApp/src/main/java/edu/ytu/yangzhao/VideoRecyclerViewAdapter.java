package edu.ytu.yangzhao;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import edu.ytu.yangzhao.databinding.VideoItemBinding;

public class VideoRecyclerViewAdapter extends RecyclerView.Adapter<VideoRecyclerViewAdapter.ViewHolder> {

    private final List<Video> videoList;

    public VideoRecyclerViewAdapter(List<Video> videoList) {
        this.videoList = videoList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        VideoItemBinding binding = VideoItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.video = videoList.get(position);
        holder.id.setText(videoList.get(position).id);
        holder.title.setText(videoList.get(position).content);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView id;
        public final TextView title;
        public Video video;

        public ViewHolder(VideoItemBinding binding) {
            super(binding.getRoot());
            id = binding.id;
            title = binding.vTitle;
        }
    }

    @Override
    public int getItemCount() {
        return videoList.size();
    }
}