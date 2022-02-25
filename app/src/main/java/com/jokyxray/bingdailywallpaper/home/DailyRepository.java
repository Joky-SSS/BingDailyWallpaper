package com.jokyxray.bingdailywallpaper.home;

import com.jokyxray.bingdailywallpaper.model.DailyModel;
import com.jokyxray.bingdailywallpaper.network.APIHelper;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class DailyRepository {

    public Observable<DailyModel> getDailyImages(){
        return APIHelper.getApi().getDailyImages().subscribeOn(Schedulers.io());
    }
}
