package com.sun.moviedb_45.service;

import android.content.Context;

import com.sun.moviedb_45.BuildConfig;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitBuilder {
    private static final String BASE_URL = "https://api.themoviedb.org/3/";
    private static final String QUERY_API_KEY = "api_key";
    private static final String API_KEY = BuildConfig.API_KEY;
    private static final int READ_TIME_OUT = 5000;
    private static final int WRITE_TIME_OUT = 5000;
    private static final int CONNECT_TIME_OUT = 5000;
    private static Retrofit sInstance;

    public static Retrofit getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(getClient(context))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return sInstance;
    }

    private static OkHttpClient getClient(Context context) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .readTimeout(READ_TIME_OUT, TimeUnit.MILLISECONDS)
                .writeTimeout(WRITE_TIME_OUT, TimeUnit.MILLISECONDS)
                .connectTimeout(CONNECT_TIME_OUT, TimeUnit.MILLISECONDS)
                .retryOnConnectionFailure(true)
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request();
                        HttpUrl httpUrl = request.url()
                                .newBuilder()
                                .addQueryParameter(QUERY_API_KEY, API_KEY)
                                .build();
                        Request.Builder requestBuilder = request.newBuilder().url(httpUrl);
                        return chain.proceed(requestBuilder.build());
                    }
                });
        return builder.build();
    }
}
