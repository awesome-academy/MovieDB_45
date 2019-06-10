package com.sun.moviedb_45.service;

import com.sun.moviedb_45.service.response.MovieResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiRequest {
    @GET("movie/{type}")
    Observable<MovieResponse> getMoviesByCategory(@Path("type") String type,
                                                  @Query("page") int page);
}
