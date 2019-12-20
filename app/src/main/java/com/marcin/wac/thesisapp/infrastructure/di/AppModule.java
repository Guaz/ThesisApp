package com.marcin.wac.thesisapp.infrastructure.di;

import android.content.Context;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.gson.GsonBuilder;
import com.marcin.wac.thesisapp.infrastructure.api.ThesisApi;
import com.marcin.wac.thesisapp.persistence.IUserSession;
import com.marcin.wac.thesisapp.persistence.UserSession;
import com.marcin.wac.thesisapp.utils.AppConfig;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class AppModule {
    private DaggApp daggApp;

    public AppModule(DaggApp daggApp) {
        this.daggApp = daggApp;
    }

    @Provides
    Context provideContext() {
        return daggApp;
    }

    @Provides
    @Singleton
    ThesisApi provideApi(UserSession userSession) {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.level(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addNetworkInterceptor(new StethoInterceptor());
        httpClient.addInterceptor(httpLoggingInterceptor);
//        httpClient.addInterceptor(chain -> {
//            Request.Builder request = chain.request().newBuilder();
//
//            if (userSession.isLoggedIn())
//                request.addHeader("Authorization", "Bearer " + userSession.getToken());
//
//            return chain.proceed(request.build());
//        });

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(AppConfig.API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .client(httpClient.build())
                .build();

        return retrofit.create(ThesisApi.class);
    }

    @Provides
    @Named("android")
    Scheduler provideAndroidScheduler() {
        return AndroidSchedulers.mainThread();
    }

    @Provides
    @Named("process")
    Scheduler provideProcessScheduler() {
        return Schedulers.io();
    }

    @Provides
    @Singleton
    IUserSession provideUserSession(Context context) {
        return new UserSession(context);
    }
}
