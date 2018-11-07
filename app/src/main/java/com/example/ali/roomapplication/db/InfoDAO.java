package com.example.ali.roomapplication.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

/**
 * Created by ali.azaz on 11/22/17.
 */

@Dao
public interface InfoDAO {

    @Query("SELECT * FROM infomodel")
    List<InfoModel> getAll();

    @Insert
    void insertAll(List<InfoModel> infoModels);

}
