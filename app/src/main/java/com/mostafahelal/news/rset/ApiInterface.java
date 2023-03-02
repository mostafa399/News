package com.mostafahelal.news.rset;

import com.mostafahelal.news.models.HomePageModel;
import com.mostafahelal.news.models.OurYtModel;
import com.mostafahelal.news.models.YTModel;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface ApiInterface {
    @GET("homepage_api")
    Call<HomePageModel>getHomePageApi(@QueryMap Map<String,String>parms);

    @GET("news_by_pid")
    Call<HomePageModel> getNewsDetailsById(@QueryMap Map<String, String> params);


    @GET("youtube")
    Call<OurYtModel> getYoutubeDetailsFromServer();

    @GET("https://www.googleapis.com/youtube/v3/activities")
    Call<YTModel> getYoutubeServerData(@QueryMap Map<String,String> params);

}
