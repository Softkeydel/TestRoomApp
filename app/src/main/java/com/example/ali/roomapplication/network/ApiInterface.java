package com.example.ali.roomapplication.network;



import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;


public interface ApiInterface {


    @GET("article.rss")
    Call<ResponseBody> loadRSSFeed();

    @GET
    Call<ResponseBody> loadRss(@Url String url);



}
