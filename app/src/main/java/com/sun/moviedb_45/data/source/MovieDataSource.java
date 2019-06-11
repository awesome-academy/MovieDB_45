package com.sun.moviedb_45.data.source;

import com.sun.moviedb_45.service.response.MovieResponse;

import io.reactivex.Observable;

public interface MovieDataSource {
    interface Local{

    }

    interface Remote{
        Observable<MovieResponse> getMovieByCategory(String type, int page);
    }
}
