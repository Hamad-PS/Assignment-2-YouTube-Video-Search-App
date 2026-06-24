package com.hamad.assignment2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoViewHolder> {

    private final List<VideoItem> videoList;

    public VideoAdapter(List<VideoItem> videoList) {
        this.videoList = videoList;
    }

    @NonNull
    @Override
    public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_video, parent, false);
        return new VideoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoViewHolder holder, int position) {
        VideoItem item = videoList.get(position);

        holder.title.setText(item.getTitle());
        holder.channel.setText("Channel: " + item.getChannelTitle());
        holder.publishTime.setText("Published: " + item.getPublishTime());
        holder.description.setText(item.getDescription());

        Glide.with(holder.itemView.getContext())
                .load(item.getThumbnailUrl())
                .into(holder.thumbnail);
    }

    @Override
    public int getItemCount() {
        return videoList.size();
    }

    static class VideoViewHolder extends RecyclerView.ViewHolder {

        ImageView thumbnail;
        TextView title;
        TextView channel;
        TextView publishTime;
        TextView description;

        VideoViewHolder(@NonNull View itemView) {
            super(itemView);
            thumbnail = itemView.findViewById(R.id.imageViewThumbnail);
            title = itemView.findViewById(R.id.textViewTitle);
            channel = itemView.findViewById(R.id.textViewChannel);
            publishTime = itemView.findViewById(R.id.textViewPublishTime);
            description = itemView.findViewById(R.id.textViewDescription);
        }
    }
}