package com.mostafahelal.news.adapter;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;


import com.mostafahelal.news.fragment.YoutubeFragment;
import com.mostafahelal.news.models.OurYtModel;

import java.util.List;

public class ViewPagerAdapter extends FragmentPagerAdapter {
Context context;
OurYtModel ourYtModel;
    public ViewPagerAdapter(@NonNull FragmentManager fm, OurYtModel ourYtModel, Context context) {
        super(fm,FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.context=context;
        this.ourYtModel=ourYtModel;
    }
    @NonNull
    @Override
    public Fragment getItem(int position) {
        Bundle bundle=new Bundle();
        bundle.putString("cid",ourYtModel.getYoutubeData().get(position).getChannelId());
        YoutubeFragment youtubeFragment=new YoutubeFragment();
        youtubeFragment.setArguments(bundle);
        return youtubeFragment;
    }
    @Override
    public int getCount() {

        return ourYtModel.getYoutubeData().size();
    }



    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return  ourYtModel.getYoutubeData().get(position).getTitle();
    }
}
