package com.example.ali.roomapplication.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

import com.example.ali.roomapplication.model.Item;
import com.example.ali.roomapplication.model.Rss;
import com.example.ali.roomapplication.util.DateFormatter;

import java.util.ArrayList;
import java.util.List;


public class SQLiteHandler extends SQLiteOpenHelper {

    private static final String TAG = SQLiteHandler.class.getSimpleName();
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "AppDB";

    //    Tables
    public static final String TABLE_RSS = "table_rss";
    public static final String TABLE_FEEDS = "table_feeds";


    //    Columns
    public static final String ID = "_id";
    public static final String TITLE = "title";
    public static final String LINK = "link";
    public static final String DESCRIPTION = "description";
    public static final String FK_RSS_ID = "rss_id";
    public static final String PUB_DATE = "publication_date";

    private static SQLiteHandler instance;
    private static SQLiteDatabase db;
    private Context context;

    public static synchronized SQLiteHandler getHelper(Context context) {
        if (instance == null){
            instance = new SQLiteHandler(context);
            db=instance.getWritableDatabase();
        }
        return instance;
    }

//    private SQLiteHandler(Context context) {
//        super(context, DATABASE_NAME, null, DATABASE_VERSION);
//    }

    private SQLiteHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (!db.isReadOnly()) {
            // Enable foreign key constraints
            //db.execSQL("PRAGMA foreign_keys=ON;");
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_RSS_TABLE = "CREATE TABLE " + TABLE_RSS + "(" + BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TITLE + " text, " + LINK + " text );";

        String CREATE_ARTICLE_TABLE = "CREATE TABLE " + TABLE_FEEDS + "(" + BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TITLE + " text, " + LINK + " text, " + DESCRIPTION + " text, " + PUB_DATE + " TIMESTAMP, "
                + FK_RSS_ID + " INTEGER REFERENCES " + TABLE_FEEDS + "(" + BaseColumns._ID + ") ON DELETE CASCADE);";

        db.execSQL(CREATE_RSS_TABLE);
        db.execSQL(CREATE_ARTICLE_TABLE);
        Log.e(TAG, "Database tables created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PROFILES);
//        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
//        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MESSAGES);
//        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CHAT_ROOMS);
//        onCreate(db);
    }






    public void insertRss(List<Rss> rssList){
        for(int i=0;i<rssList.size();i++){
            ContentValues genreCV=new ContentValues();
            genreCV.put(TITLE,rssList.get(i).getTitle());
            genreCV.put(LINK,rssList.get(i).getLink());
            db.insert(TABLE_RSS,null,genreCV);
        }
    }

    public List<Rss> getAllRssList(){
        Cursor cursor = db.rawQuery("SELECT * FROM "+TABLE_RSS, new String[]{});
        List<Rss> rssList = new ArrayList<Rss>();
        if (cursor != null) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {

                rssList.add(new Rss(cursor.getLong(cursor.getColumnIndex(ID)),
                        cursor.getString(cursor.getColumnIndex(TITLE)),
                        cursor.getString(cursor.getColumnIndex(LINK))));
                cursor.moveToNext();
            }
            cursor.close();
        }
        return rssList;
    }

    public void insertInDBFeeds(List<Item> items,long rssId){
        for(int i=0;i<items.size();i++){
            ContentValues cv=new ContentValues();
            cv.put(TITLE,items.get(i).getTitle());
            cv.put(DESCRIPTION,items.get(i).getDescription());
            cv.put(LINK,items.get(i).getLink());
            cv.put(PUB_DATE, DateFormatter.getDate(items.get(i).getPubDate()));
            cv.put(FK_RSS_ID,rssId);

            db.insert(TABLE_FEEDS,null,cv);
        }
    }

    public List<Item> getAllFeeds(String rssId){
        Cursor cursor = db.rawQuery("SELECT * FROM "+TABLE_FEEDS + " WHERE " + FK_RSS_ID + " = ? ORDER BY "+PUB_DATE+ " DESC ", new String[]{rssId});
        List<Item> feeds = new ArrayList<Item>();
        if (cursor != null) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                feeds.add(new Item(cursor.getLong(cursor.getColumnIndex(ID)),
                        cursor.getString(cursor.getColumnIndex(TITLE)),
                        cursor.getString(cursor.getColumnIndex(DESCRIPTION)),
                        cursor.getString(cursor.getColumnIndex(LINK)),
                        cursor.getString(cursor.getColumnIndex(PUB_DATE))));
                cursor.moveToNext();
            }
            cursor.close();
        }
        return feeds;
    }




}