package com.jokyxray.bingdailywallpaper.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.jokyxray.bingdailywallpaper.model.DailyImage;

import java.util.List;

@Dao
public interface DailyImageDao {

    @Query("select * from dailyimage where enddate = :date")
    DailyImage getDailyByDate(String date);

    @Query("select * from dailyimage order by sort desc")
    List<DailyImage> getAllDailyImage();

    @Query("select hsh from dailyimage where hsh = :hsh")
    String getHsh(String hsh);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertDailyImage(DailyImage dailyImage);
}
