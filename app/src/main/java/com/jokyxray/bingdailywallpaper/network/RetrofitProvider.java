package com.jokyxray.bingdailywallpaper.network;



import com.jokyxray.bingdailywallpaper.BuildConfig;
import com.jokyxray.bingdailywallpaper.uitls.Constant;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetrofitProvider {

    private RetrofitProvider() {
    }

    public static Retrofit getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static final Retrofit INSTANCE = create();

        private static Retrofit create() {
            OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
            builder.readTimeout(10, TimeUnit.SECONDS);
            builder.connectTimeout(10, TimeUnit.SECONDS);
            if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
                logging.setLevel(HttpLoggingInterceptor.Level.HEADERS);
                builder.addInterceptor(logging);
            }
            OkHttpClient OkHttpClient = builder.build();
            return new Retrofit.Builder().baseUrl(Constant.HOST)
                           .client(OkHttpClient)
                           .addConverterFactory(GsonConverterFactory.create())
                           .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                           .build();
        }
    }
}
