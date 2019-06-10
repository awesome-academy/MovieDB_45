package com.sun.moviedb_45.service;

import android.content.Context;

public class Service {
    private static ApiRequest sInstance;

    public static ApiRequest getInstance(Context context){
        if (sInstance == null){
            sInstance = RetrofitBuilder.getInstance(context).create(ApiRequest.class);
        }
        return sInstance;
    }
}
