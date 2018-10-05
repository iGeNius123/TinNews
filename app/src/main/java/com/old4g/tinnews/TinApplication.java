package com.old4g.tinnews;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.facebook.stetho.Stetho;
import com.old4g.tinnews.database.NewsDao;
import com.old4g.tinnews.database.NewsDao.AppDatabase;
public class TinApplication extends Application {
    //init Room
    public static AppDatabase database;
    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);

        //init Room
        database = Room.databaseBuilder(getApplicationContext(),NewsDao.AppDatabase.class,"rin_db").build();

    }

    //get data base
    public static AppDatabase getDataBase(){
        return database;
    }

}

