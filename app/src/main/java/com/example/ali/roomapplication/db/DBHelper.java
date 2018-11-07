package com.example.ali.roomapplication.db;

import android.arch.persistence.room.Room;
import android.content.Context;

/**
 * Created by ali.azaz on 11/23/17.
 */

public class DBHelper {

    AppDB roomDB;

    public DBHelper(Context context){

        //        ROOM roomDB connection
        roomDB = Room.databaseBuilder(context,AppDB.class,"contact-directory-roomDB").build();

        /* If you modify the database after the installation of App then we've to use MIGRATION
        Refer: https://developer.android.com/training/data-storage/room/migrating-db-versions.html */

        /*AppDB roomDB = Room.databaseBuilder(context,AppDB.class,"contact-directory-roomDB")
                .addMigrations(MIGRATION_1_2, MIGRATION_2_3).build();*/
    }

    public AppDB ReturnDB(){
        return roomDB;
    }

/*    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE `Fruit` (`id` INTEGER, "
                    + "`name` TEXT, PRIMARY KEY(`id`))");
        }
    };

    static final Migration MIGRATION_2_3 = new Migration(2, 3) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE Book "
                    + " ADD COLUMN pub_year INTEGER");
        }
    };*/
}


