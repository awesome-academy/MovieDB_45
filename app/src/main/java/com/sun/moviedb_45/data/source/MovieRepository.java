package com.sun.moviedb_45.data.source;

import com.sun.moviedb_45.data.source.local.MovieLocalDataSource;
import com.sun.moviedb_45.data.source.remote.MovieRemoteDataSource;
import com.sun.moviedb_45.service.response.MovieResponse;

import io.reactivex.Observable;

public class MovieRepository implements MovieDataSource.Local, MovieDataSource.Remote {
    private static MovieRepository sInstance;
    private MovieDataSource.Remote mRemoteDataSource;
    private MovieDataSource.Local mLocalDataSource;

    private MovieRepository(MovieDataSource.Remote remote,
                            MovieDataSource.Local local) {
        mRemoteDataSource = remote;
        mLocalDataSource = local;
    }

    public static MovieRepository getInstance(MovieRemoteDataSource remote,
                                              MovieLocalDataSource local) {
        if (sInstance == null) {
            sInstance = new MovieRepository(remote, local);
        }
        return sInstance;
    }

    @Override
    public Observable<MovieResponse> getMovieByCategory(String type, int page) {
        return mRemoteDataSource.getMovieByCategory(type, page);
    }
}
