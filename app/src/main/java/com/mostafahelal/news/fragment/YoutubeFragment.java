package com.mostafahelal.news.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.mostafahelal.news.R;
import com.mostafahelal.news.adapter.YoutubeAdapter;
import com.mostafahelal.news.models.YTModel;
import com.mostafahelal.news.rset.ApiClient;
import com.mostafahelal.news.rset.ApiInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class YoutubeFragment extends Fragment {
    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefreshLayout;
    ProgressBar progressBar;
    Context context;
    String cid, pageToken= "";
    List<YTModel.Item> items = new ArrayList<>();
    YoutubeAdapter youtubeAdapter;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_youtube, container,false);
        recyclerView  = view.findViewById(R.id.video_recy);
        swipeRefreshLayout = view.findViewById(R.id.swipe);
        progressBar = view.findViewById(R.id.progressBar);

        youtubeAdapter=  new YoutubeAdapter(context, items);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(linearLayoutManager);
        cid = getArguments().getString("cid");
        loadDataFromYoutubeChannel();
        return view;
    }

    private void loadDataFromYoutubeChannel() {
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Map<String, String> params = new HashMap<>();
        params.put("part", "snippet");
        params.put("channelId", cid);
        params.put("maxResults", "5");
        params.put("pageToken", pageToken);
        params.put("key", getString(R.string.google_api_key));
        Call<YTModel> call = apiInterface.getYoutubeServerData(params);
        call.enqueue(new Callback<YTModel>() {
            @Override
            public void onResponse(Call<YTModel> call, Response<YTModel> response) {
                items.clear();
                assert response.body() != null;
                items.addAll(response.body().getItems());
                recyclerView.setAdapter(youtubeAdapter);
            }

            @Override
            public void onFailure(Call<YTModel> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                swipeRefreshLayout.setRefreshing(false);
                progressBar.setVisibility(View.GONE);
            }
        });

    }
}