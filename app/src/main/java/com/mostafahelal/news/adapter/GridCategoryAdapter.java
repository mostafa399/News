package com.mostafahelal.news.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.mostafahelal.news.R;
import com.mostafahelal.news.models.HomePageModel;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class GridCategoryAdapter extends BaseAdapter {
    LayoutInflater layoutInflater;
    Context context;
    List<HomePageModel.CategoryButton> categoryButtons;

    public GridCategoryAdapter( Context context,List<HomePageModel.CategoryButton>categoryList) {
        this.context = context;
       layoutInflater=(LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
       this.categoryButtons=categoryList;

    }


    @Override
    public int getCount() {
        return categoryButtons.size();
    }

    @Override
    public Object getItem(int position) {
        return categoryButtons.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        GridViewHolder holder=null;
        if (convertView==null){
            convertView=layoutInflater.inflate(R.layout.item_category_layout,null);
            holder=new GridViewHolder();
            holder.circleImageView=convertView.findViewById(R.id.category_image);
            holder.textView=convertView.findViewById(R.id.text_category);
            convertView.setTag(holder);

        }else {
            holder= (GridViewHolder) convertView.getTag();

        }
        holder.textView.setText(categoryButtons.get(position).getName());
        Glide.with(context).load(categoryButtons.get(position).getImage()).into(holder.circleImageView);
        if (categoryButtons.get(position).getColor()!=null) {
            holder.circleImageView.setCircleBackgroundColor(Color.parseColor(categoryButtons.get(position).getColor()));
            holder.circleImageView.setBorderColor(Color.parseColor(categoryButtons.get(position).getColor()));
        }
        return convertView;

    }
   private static class GridViewHolder{
        TextView textView;
        CircleImageView circleImageView;

    }





}
