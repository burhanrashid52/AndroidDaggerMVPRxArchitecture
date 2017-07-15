package com.burhan.rashid.daggermvprx.data.module;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.burhan.rashid.daggermvprx.data.NetworkScope;
import com.burhan.rashid.daggermvprx.networks.ApiService;
import com.burhan.rashid.daggermvprx.networks.SignedRequestInterceptor;
import com.burhan.rashid.daggermvprx.util.ApiConstants;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Burhanuddin Rashid on 21-Apr-16.
 */
@Module
public class NetModule {
    private String mBaseUrl = ApiConstants.BASE_URL;

    @Provides
    @NetworkScope
    SharedPreferences providesSharedPreferences(Application application) {
        return PreferenceManager.getDefaultSharedPreferences(application);
    }

    @Provides
    @NetworkScope
    Cache provideHttpCache(Application application) {
        int cacheSize = 10 * 1024 * 1024;
        Cache cache = new Cache(application.getCacheDir(), cacheSize);
        return cache;
    }

    @Provides
    @NetworkScope
    Gson provideGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        return gsonBuilder.create();
    }

    @Provides
    @NetworkScope
    @Named("refresh_client")
    OkHttpClient provideOkhttpClientRefresh(@Named("api_refresh") Retrofit retrofit, Cache cache, HttpLoggingInterceptor interceptor) {
        SignedRequestInterceptor signedRequestInterceptor = new SignedRequestInterceptor(retrofit);
        OkHttpClient.Builder client = new OkHttpClient.Builder()
                .connectTimeout(ApiConstants.HTTP_CONNECT_TIMEOUT, TimeUnit.MILLISECONDS)
                .readTimeout(ApiConstants.HTTP_READ_TIMEOUT, TimeUnit.MILLISECONDS)
                .addInterceptor(interceptor)
                .addInterceptor(signedRequestInterceptor);
        // client.cache(cache);
        return client.build();
    }

    @Provides
    @NetworkScope
    @Named("client")
    OkHttpClient provideOkhttpClient(Cache cache, HttpLoggingInterceptor interceptor) {
        OkHttpClient.Builder client = new OkHttpClient.Builder()
                .connectTimeout(ApiConstants.HTTP_CONNECT_TIMEOUT, TimeUnit.MILLISECONDS)
                .readTimeout(ApiConstants.HTTP_READ_TIMEOUT, TimeUnit.MILLISECONDS)
                .addInterceptor(interceptor);
        // client.cache(cache);
        return client.build();
    }

    @Provides
    @NetworkScope
    @Named("api_call")
    Retrofit provideRetrofit(Gson gson, @Named("client") OkHttpClient okHttpClient) {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(mBaseUrl)
                .client(okHttpClient)
                .build();
        return retrofit;
    }

    @Provides
    @NetworkScope
    @Named("api_refresh")
    Retrofit provideRetrofitForApiRefresh(Gson gson) {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(mBaseUrl)
                .build();
        return retrofit;
    }

    @Provides
    @NetworkScope
    HttpLoggingInterceptor provideHttpLoggingInterceptor() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return interceptor;
    }

    @Provides
    @NetworkScope
    ApiService provideApiService(@Named("api_call") Retrofit retrofit) {
        return retrofit.create(ApiService.class);
    }
}
