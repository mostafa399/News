package com.mostafahelal.news.rset;

import androidx.appcompat.app.AppCompatActivity;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient extends AppCompatActivity {
    //192.168.1.20
    public static String BASE_URL="http://10.0.2.2/newsapp/wp-json/api/";
    public static String BASE_URL_REAL="http://192.168.1.20/newsapp/wp-json/api/";
    public static Retrofit retrofit=null;
    public static Retrofit getApiClient(){
        HttpLoggingInterceptor httpLoggingInterceptor=new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client=new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build();
        if (retrofit==null){
            retrofit=new Retrofit.Builder().baseUrl(BASE_URL_REAL).client(client)
                    .addConverterFactory(GsonConverterFactory.create()).build();
        }return retrofit;

    }
}
