package com.example.ali.roomapplication.activity;

import android.databinding.DataBindingUtil;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.ali.roomapplication.R;
import com.example.ali.roomapplication.adapter.FeedAdapter;
import com.example.ali.roomapplication.adapter.RssAdapter;
import com.example.ali.roomapplication.databinding.ActivityFeedBinding;
import com.example.ali.roomapplication.db.SQLiteHandler;
import com.example.ali.roomapplication.model.Item;
import com.example.ali.roomapplication.model.RSSFeedJackson;
import com.example.ali.roomapplication.model.Rss;

import java.util.List;
import java.util.Random;

public class FeedActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ActivityFeedBinding binding;

    private FeedAdapter mAdapter;
    private List<Item> feedList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_feed);

        feedList= (List<Item>) getIntent().getSerializableExtra("feedList");
        System.out.println("feedList:"+feedList);
        if(feedList==null){
            finish();
        }else {
            initRecyclerView();
        }

        binding.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // feedList.get(0).setTitle(feedList.get(0).getTitle()+getRandomNumber(0,50));
            }
        });
    }

    private int getRandomNumber(int min,int max) {
        return (new Random()).nextInt((max - min) + 1) + min;
    }




    private void initRecyclerView() {
        recyclerView = binding.rv;
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new FeedAdapter(this,feedList);
        recyclerView.setAdapter(mAdapter);
    }




}
