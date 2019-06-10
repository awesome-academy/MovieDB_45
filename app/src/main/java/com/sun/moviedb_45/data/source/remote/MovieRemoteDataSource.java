package com.sun.moviedb_45.data.source.remote;

import android.content.Context;

import com.sun.moviedb_45.data.source.MovieDataSource;
import com.sun.moviedb_45.service.ApiRequest;
import com.sun.moviedb_45.service.Service;
import com.sun.moviedb_45.service.response.MovieResponse;

import io.reactivex.Observable;

public class MovieRemoteDataSource implements MovieDataSource.Remote {
    private static MovieRemoteDataSource sInstance;
    private ApiRequest mApiRequest;

    private MovieRemoteDataSource(ApiRequest request) {
        mApiRequest = request;
    }

    public static MovieRemoteDataSource getInstance(Context context){
        if (sInstance==null){
            sInstance = new MovieRemoteDataSource(Service.getInstance(context));
        }
        return sInstance;
    }

    @Override
    public Observable<MovieResponse> getMovieByCategory(String type, int page) {
        return mApiRequest.getMoviesByCategory(type,page);
    }
}
