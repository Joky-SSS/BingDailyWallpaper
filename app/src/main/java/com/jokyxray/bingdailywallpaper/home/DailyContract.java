package com.jokyxray.bingdailywallpaper.home;

import android.app.Activity;

import com.jokyxray.bingdailywallpaper.model.DailyImage;

import java.util.List;

public interface DailyContract {
    interface View{
        Activity getActivity();
        void onDailyImageLoad(List<DailyImage> imageList);
        void onImageDownloaded();
    }

    interface Presenter{
        void loadDailyImage();
    }
}
