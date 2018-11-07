package com.example.ali.roomapplication.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.ali.roomapplication.model.Item;

/**
 * Created by ali.azaz on 11/22/17.
 */

@Database(entities = {DirectoryModel.class,InfoModel.class},version = 1)
public abstract class AppDB extends RoomDatabase {
    public abstract DirectoryDAO daoAccess();
    public abstract InfoDAO infoDAO();
    //public abstract FeedsDao feedsDao();
}
