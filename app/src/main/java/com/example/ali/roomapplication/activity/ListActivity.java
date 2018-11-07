package com.example.ali.roomapplication.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.ali.roomapplication.R;
import com.example.ali.roomapplication.adapter.RssAdapter;
import com.example.ali.roomapplication.databinding.ActivityListBinding;
import com.example.ali.roomapplication.db.SQLiteHandler;
import com.example.ali.roomapplication.model.Item;
import com.example.ali.roomapplication.model.RSSFeedJackson;
import com.example.ali.roomapplication.model.Rss;
import com.example.ali.roomapplication.network.ApiClient;
import com.example.ali.roomapplication.network.ApiInterface;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListActivity extends AppCompatActivity implements RssAdapter.RssClickListener {

    private RecyclerView recyclerView;
    private View bottomSheet;

    private BottomSheetBehavior behavior;
    private ActivityListBinding binding;

    private RssAdapter mAdapter;
    private SQLiteHandler dbHelper;
    //private List<Rss> rssList;
    //private List<Item> feedList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_list);

        dbHelper=SQLiteHandler.getHelper(this);

        new LoadRssFromDB().execute();

        bottomSheet = binding.bottomSheet;
        behavior = BottomSheetBehavior.from(binding.bottomSheet);
        behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            DateTimeFormatter.RFC_1123_DATE_TIME.parse("Tue, 19 Oct 2004 11:08:56 -0400");
//        }

    }

    @Override
    public void onItemClicked(Rss rss) {
        //new LoadFeedFromDB(rss).execute();

        System.out.println("bottomSheet:"+binding.bottomSheet);
        bottomSheet = binding.bottomSheet;
        behavior = BottomSheetBehavior.from(binding.bottomSheet);
        behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);

    }




    private class LoadRssFromDB extends AsyncTask<Void, Void, Void> {
        private List<Rss> rssList;

        @Override
        protected Void doInBackground(Void... params) {
            rssList=dbHelper.getAllRssList();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            System.out.println("########################### LOADAING COMPLETED ################################");
            System.out.println("rssList:"+rssList.size());
            if(rssList.size()==0){
                new SaveRssInDB().execute();
            }else {
                initRecyclerView(rssList);
            }
        }

        @Override
        protected void onPreExecute() {
            System.out.println("############################### LOADAING STARTED ############################");
        }
    }

    private class SaveRssInDB extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            dbHelper.insertRss(new ArrayList<Rss>(){{
                add(new Rss("Unifiedinfotech Pvt Ltd","http://www.feedforall.com/sample.xml"));
                add(new Rss("Unifiedinfotech Pvt Ltd","http://www.feedforall.com/sample.xml"));
                add(new Rss("Unifiedinfotech Pvt Ltd","http://www.feedforall.com/sample.xml"));
            }});
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            System.out.println("########################### INSERT COMPLETED ################################");
            new LoadRssFromDB().execute();
        }

        @Override
        protected void onPreExecute() {
            System.out.println("############################### INSERT STARTED ############################");
        }
    }

    private class LoadFeedFromDB extends AsyncTask<Void, Void, Void> {
        private Rss rss;
        private List<Item> feedList;

        public LoadFeedFromDB(Rss rss) {
            this.rss=rss;
        }

        @Override
        protected Void doInBackground(Void... params) {
            feedList=dbHelper.getAllFeeds(String.valueOf(rss.getId()));
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            System.out.println("########################### LOADAING COMPLETED ################################");
            System.out.println("feedList:"+feedList.size());
            if(feedList.size()==0){
                loadRss(rss);
            }else {
                startActivity(new Intent(ListActivity.this,FeedActivity.class)
                .putExtra("feedList", (Serializable) feedList));
            }
        }

        @Override
        protected void onPreExecute() {
            System.out.println("############################### LOADAING STARTED ############################");
        }
    }

    private class SaveFeedInDB extends AsyncTask<Void, Void, Void> {
        private Rss rss;
        private List<Item> feedList;

        public SaveFeedInDB(Rss rss, List<Item> feedList) {
            this.rss=rss;
            this.feedList=feedList;
        }

        @Override
        protected Void doInBackground(Void... params) {
            dbHelper.insertInDBFeeds(feedList,rss.getId());
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            System.out.println("########################### INSERT COMPLETED ################################");
            new LoadFeedFromDB(rss).execute();
        }

        @Override
        protected void onPreExecute() {
            System.out.println("############################### INSERT STARTED ############################");
        }
    }




    private int getRandomNumber(int min,int max) {
        return (new Random()).nextInt((max - min) + 1) + min;
    }



    private void initRecyclerView(List<Rss> rssList) {
        recyclerView = binding.rv;
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new RssAdapter(this,rssList);
        recyclerView.setAdapter(mAdapter);
    }

    private void saveRss(final RSSFeedJackson rss){

        new Thread(new Runnable() {
            @Override
            public void run() {
                //List<Item> products = AppDB.getDB(ListActivity.this).rssDao().getAll();

                //DBHelper database = new DBHelper(getApplicationContext());
                //database.ReturnDB().rssDao().insertAll(rss.getChannel().getItem());


            }
        }).start();

    }

    private void getRss(){

        new Thread(new Runnable() {
            @Override
            public void run() {
                //List<Item> items = AppDB.getDB(ListActivity.this).rssDao().getAll();

               // System.out.println("items:"+items.size());


            }
        }).start();

    }

    private void loadRss() {

        Call<ResponseBody> call = ApiClient.getClient().create(ApiInterface.class)
                .loadRss("http://www.feedforall.com/sample.xml");

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                try {
                    String xmlData=response.body().string();

                    System.out.println("response :"+xmlData);

                    ObjectMapper objectMapper = new XmlMapper();

                    //Channel channel = objectMapper.readValue(xmlData,Channel.class);
                    RSSFeedJackson rss = objectMapper.readValue(xmlData,RSSFeedJackson.class);
                    //RSSFeedJackson rss = objectMapper.readValue(new URL("http://vogella.com/"),RSSFeedJackson.class);

                    System.out.println("rss:"+rss.getVersion());
                    System.out.println("rss:"+rss.getChannel().getItem().size());

                    //initRecyclerView(rss);

                    saveRss(rss);


                } catch (Exception e) {
                    System.out.println("Exception:"+e);
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                System.out.println("Throwable:"+t.getMessage());

            }
        });
    }

    private void loadRss(final Rss rss) {

        Call<ResponseBody> call = ApiClient.getClient().create(ApiInterface.class).loadRss(rss.getLink());
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                try {
                    String xmlData=response.body().string();

                    System.out.println("response :"+xmlData);

                    ObjectMapper objectMapper = new XmlMapper();

                    RSSFeedJackson rssResponse = objectMapper.readValue(xmlData,RSSFeedJackson.class);

                    System.out.println("rssResponse:"+rssResponse.getVersion());
                    System.out.println("rssResponse:"+rssResponse.getChannel().getItem().size());

                   // initRecyclerView(rss);

                    //saveRss(rss);

                    new SaveFeedInDB(rss,rssResponse.getChannel().getItem()).execute();


                } catch (Exception e) {
                    System.out.println("Exception:"+e);
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                System.out.println("Throwable:"+t.getMessage());

            }
        });
    }

}
