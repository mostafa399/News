package com.mostafahelal.news.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.mostafahelal.news.activity.NewsDetailActivity;
import com.mostafahelal.news.R;
import com.mostafahelal.news.models.HomePageModel;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<HomePageModel.News> news;
    int image_left=1;
    int image_top=2;

    public NewsAdapter(Context context, List<HomePageModel.News> news) {
        this.context = context;
        this.news = news;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == image_left){
            View v = LayoutInflater.from(context).inflate(R.layout.item_news,parent, false);
            return new NewsViewHolder(v);
        }
        else{
            View v = LayoutInflater.from(context).inflate(R.layout.item_news_image,parent, false);
            return new NewsViewHolder(v);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        HomePageModel.News singleNews = news.get(holder.getAdapterPosition());

        NewsViewHolder viewHolder = (NewsViewHolder) holder;
        viewHolder.newsTitle.setText(removeHtml(singleNews.getTitle()).trim());
        viewHolder.newsDesc.setText(removeHtml(singleNews.getPostContent()).trim());

        if (singleNews.getSource() != null){
            viewHolder.newsSource.setText(singleNews.getSource());
        }

        if(singleNews.getImage().length() <=1){
            viewHolder.newsImage.setVisibility(View.GONE);
        }else{
            viewHolder.newsImage.setVisibility(View.VISIBLE);
            try {
                Glide.with(context)
                        .load(singleNews.getImage())
                        .into(viewHolder.newsImage);

            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        }

    }

    @Override
    public int getItemCount() {
        return news.size();
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        View holder;
        ImageView newsImage;
        TextView newsTitle, newsDesc, newsDate, newsSource, newsView;
        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            holder = itemView;
            newsImage = holder.findViewById(R.id.news_image);
            newsTitle = holder.findViewById(R.id.news_title);
            newsDesc  = holder.findViewById(R.id.news_desc);
            newsDate  = holder.findViewById(R.id.news_date);
            newsView  = holder.findViewById(R.id.news_view);
            newsSource= holder.findViewById(R.id.news_source);

            holder.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent i  = new Intent(context, NewsDetailActivity.class);
            i.putExtra("pid", news.get(getAdapterPosition()).getPid());
            context.startActivity(i);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if ((position+1)%8 == 0 || position == 0){
            // Loading the first item & every 8 items the big layout
            return image_top;
        }else{
            return image_left;
        }
    }
    public static String removeHtml(String html){
        html = html.replaceAll("<(.*?)\\>"," ");//Removes all items in brackets
        html = html.replaceAll("<(.*?)\\\n"," ");//Must be undeneath
        html = html.replaceFirst("(.*?)\\>", " ");//Removes any connected item to the last bracket
        html = html.replaceAll("&nbsp;"," ");
        html = html.replaceAll("&amp;"," ");
        return html;
        // Check the description and the source code if you want it
    }
}
