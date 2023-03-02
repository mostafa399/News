package com.mostafahelal.news;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ProgressBar;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.slider.Slider;
import com.mostafahelal.news.activity.YoutubeActivity;
import com.mostafahelal.news.adapter.GridCategoryAdapter;
import com.mostafahelal.news.adapter.NewsAdapter;
import com.mostafahelal.news.models.HomePageModel;
import com.mostafahelal.news.rset.ApiClient;
import com.mostafahelal.news.rset.ApiInterface;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    private FloatingActionButton fab;
    private SliderLayout sliderLayout;
    private GridView gridView;
    private RecyclerView recyclerView;
    private NewsAdapter newsAdapter;
    private List<HomePageModel.News>news;
    private List<HomePageModel.CategoryButton>categoryButtons;
    private ProgressBar progressBar;
    private NestedScrollView nestedScrollView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private GridCategoryAdapter adapter;
    private int posts=3;
    private int page=1;
    private boolean isFromStart=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       // page=1;
       // isFromStart=true;
        initiateViews();
        addImagesToSlider();
        getHomeData();
        nestedScrollView.setOnScrollChangeListener((NestedScrollView.OnScrollChangeListener) (v, scrollX, scrollY, oldScrollX, oldScrollY) -> {
            if (scrollY==(v.getChildAt(0).getMeasuredHeight()-v.getMeasuredHeight())){
               isFromStart=false;
               progressBar.setVisibility(View.VISIBLE);
               page++;
            }
        });
    }

    private void getHomeData() {
        ApiInterface apiInterface= ApiClient.getApiClient().create(ApiInterface.class);
        Map<String,String>parms=new HashMap<>();
        parms.put("page",page+"");
        parms.put("posts",posts+"");
        Call<HomePageModel>call=apiInterface.getHomePageApi(parms);
       call.enqueue(new Callback<HomePageModel>() {
           @Override
           public void onResponse(Call<HomePageModel> call, Response<HomePageModel> response) {
               updateDataOnHomePage(response.body());
           }

           @Override
           public void onFailure(Call<HomePageModel> call, Throwable t) {
               progressBar.setVisibility(View.GONE);
               swipeRefreshLayout.setRefreshing(false);

           }
       });
    }

    private void updateDataOnHomePage(HomePageModel body) {
        progressBar.setVisibility(View.GONE);
        swipeRefreshLayout.setRefreshing(false);
        if (isFromStart){
            news.clear();
            categoryButtons.clear();
        }
        for (int i=0;i<body.getBanners().size();i++){
            DefaultSliderView defaultSliderView=new DefaultSliderView(this);
            defaultSliderView.image(body.getBanners().get(i).getImage());
            defaultSliderView.setOnSliderClickListener(slider -> {

            });
            sliderLayout.addSlider(defaultSliderView);

        }
        sliderLayout.startAutoCycle();
        sliderLayout.setPresetTransformer(SliderLayout.Transformer.Stack);
        sliderLayout.setDuration(3000);
        sliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Right_Bottom);



        int beforeNewsSize=news.size();


        for (int i=0;i<body.getNews().size();i++){
            news.add(body.getNews().get(i));
        }
        categoryButtons.addAll(body.getCategoryButton());
        if (isFromStart){
            recyclerView.setAdapter(newsAdapter);
            gridView.setAdapter(adapter);

        }else {
            newsAdapter.notifyItemRangeInserted(beforeNewsSize,body.getNews().size());
        }




    }

    private void addImagesToSlider() {

    }

    private void initiateViews() {


        // Fab
        fab = findViewById(R.id.floatings);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this, YoutubeActivity.class);
                startActivity(i);
            }
        });

        sliderLayout = findViewById(R.id.slider);
        gridView = findViewById(R.id.grid_view);
        categoryButtons=new ArrayList<>();
        adapter=new GridCategoryAdapter(this,categoryButtons);

        progressBar = findViewById(R.id.progressBar);

        // Nested scrollview
        nestedScrollView = findViewById(R.id.nested);

        // RecyclerView
        recyclerView = findViewById(R.id.recy_news);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setNestedScrollingEnabled(false);
        news=new ArrayList<>();
        newsAdapter=new NewsAdapter(this,news);

        swipeRefreshLayout = findViewById(R.id.swipy);
        swipeRefreshLayout.setRefreshing(true);
        swipeRefreshLayout.setColorSchemeResources(R.color.orange,
                R.color.blue,
                R.color.green);
        swipeRefreshLayout.setOnRefreshListener(this);

    }

    @Override
    protected void onStop() {
        super.onStop();
        sliderLayout.stopAutoCycle();
        swipeRefreshLayout.setRefreshing(false);
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onRefresh() {
        isFromStart=true;


    }
}