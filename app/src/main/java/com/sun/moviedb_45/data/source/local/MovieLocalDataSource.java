package com.sun.moviedb_45.data.source.local;

import android.content.Context;

import com.sun.moviedb_45.data.source.MovieDataSource;

public class MovieLocalDataSource implements MovieDataSource.Local {
    private static MovieLocalDataSource sInstance;

    public static MovieLocalDataSource getInstance(Context context){
        if (sInstance == null){
            sInstance = new MovieLocalDataSource();
        }
        return sInstance;
    }
}
