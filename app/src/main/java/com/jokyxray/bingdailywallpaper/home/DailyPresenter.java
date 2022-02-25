package com.jokyxray.bingdailywallpaper.home;

import android.net.Uri;

import com.jokyxray.bingdailywallpaper.dao.AppDatabase;
import com.jokyxray.bingdailywallpaper.dao.DailyImageDao;

import com.jokyxray.bingdailywallpaper.model.DailyImage;
import com.jokyxray.bingdailywallpaper.model.DailyModel;
import com.jokyxray.bingdailywallpaper.network.APIHelper;
import com.jokyxray.bingdailywallpaper.uitls.Constant;
import com.jokyxray.bingdailywallpaper.uitls.FileHelper;


import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class DailyPresenter implements DailyContract.Presenter {

    DailyContract.View mView;
    DailyRepository repository = new DailyRepository();
    DailyImageDao imageDao;
    public DailyPresenter(DailyContract.View mView) {
        this.mView = mView;
        imageDao = AppDatabase.get(mView.getActivity()).dailyDao();
    }

    @Override
    public void loadDailyImage() {


        repository.getDailyImages().flatMap((Function<DailyModel, ObservableSource<DailyImage>>) dailyModel -> Observable.fromIterable(dailyModel.getImages()))
        .doOnNext(dailyImage -> {
            Uri uri = Uri.parse(dailyImage.getCopyrightlink());
            String q = uri.getQueryParameter("q");
            dailyImage.setTitle(q);
            String hsh = imageDao.getHsh(dailyImage.getHsh());
            if(hsh == null)
                imageDao.insertDailyImage(dailyImage);
            if(!checkImage(dailyImage.getHsh())){
                downloadImage(dailyImage.getUrl(),dailyImage.getHsh());
            }
        }).collect(ArrayList<DailyImage>::new, ArrayList::add)
        .subscribe(imageList -> mView.onDailyImageLoad(imageList));
    }

    private boolean checkImage(String hsh) {
        File folder = mView.getActivity().getExternalFilesDir("image");
        return new File(folder,hsh).exists();
    }

    void downloadImage(String url,String hsh){
        APIHelper.getApi().downloadImage(Constant.HOST+url).subscribeOn(Schedulers.io())
                .subscribe(responseBody -> {
                    boolean result = FileHelper.writeResponseBodyToDisk(responseBody,
                            mView.getActivity().getExternalFilesDir("image").getAbsolutePath()+"/"+hsh);
                    if(result) mView.onImageDownloaded();
                });
    }

}
