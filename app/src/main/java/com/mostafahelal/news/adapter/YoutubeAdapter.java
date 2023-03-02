package com.mostafahelal.news.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.mostafahelal.news.R;
import com.mostafahelal.news.activity.YoutubeVideoActivity;
import com.mostafahelal.news.models.YTModel;

import java.util.List;

public class YoutubeAdapter extends RecyclerView.Adapter<YoutubeAdapter.YoutubeViewHolder> {
    Context context;
    List<YTModel.Item> items;

    public YoutubeAdapter(Context context, List<YTModel.Item> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public YoutubeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_video, parent,false);
        return new YoutubeViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull YoutubeViewHolder holder, int position) {
        YTModel.Item singleVideoItem = items.get(holder.getAdapterPosition());

        if (singleVideoItem.getSnippet().getThumbnails().getStandard() != null){
            Glide.with(context)
                    .load(singleVideoItem.getSnippet().getThumbnails()
                            .getStandard().getUrl())
                    .into(holder.videoThumbnail);
        }else{
            Glide.with(context)
                    .load(singleVideoItem.getSnippet().getThumbnails()
                            .getDefault().getUrl())
                    .into(holder.videoThumbnail);
        }


        holder.videoTitle.setText(singleVideoItem.getSnippet().getTitle());


    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class YoutubeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView videoThumbnail;
        TextView videoTitle;
        View view;
        public YoutubeViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;

            videoThumbnail = view.findViewById(R.id.thumbnail);
            videoTitle = view.findViewById(R.id.video_title);

            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            YTModel.Item clickedVideo = items.get(getAdapterPosition());
            Uri uri = Uri.parse(clickedVideo.getSnippet().getThumbnails()
                    .getDefault().getUrl());

            Intent i = new Intent(context, YoutubeVideoActivity.class);
            i.putExtra("vid" , uri.getPathSegments().get(1));
            i.putExtra("cid" , clickedVideo.getSnippet().getChannelId());
            i.putExtra("cname", clickedVideo.getSnippet().getChannelTitle());
            i.putExtra("title" , clickedVideo.getSnippet().getTitle());

            context.startActivity(i);
        }
    }
}
