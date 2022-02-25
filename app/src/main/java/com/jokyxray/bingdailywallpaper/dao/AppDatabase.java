package com.jokyxray.bingdailywallpaper.dao;

import android.content.Context;


import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.jokyxray.bingdailywallpaper.model.DailyImage;


@Database(entities = {DailyImage.class},version = 1,exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private static final String dbName = "daily_wp";
    private static AppDatabase INSTANCE;
    private static final Object sLock = new Object();
    public abstract DailyImageDao dailyDao();

    public static AppDatabase get(Context context){
        synchronized (sLock){
            if (INSTANCE == null) INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, dbName).build();
            return INSTANCE;
        }
    }
}
