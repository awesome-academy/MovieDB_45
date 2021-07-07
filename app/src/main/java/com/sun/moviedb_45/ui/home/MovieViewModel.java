package com.sun.moviedb_45.ui.home;

import android.databinding.BaseObservable;

import com.sun.moviedb_45.data.model.Movie;

public class MovieViewModel extends BaseObservable {
    private Movie mMovie;

    public MovieViewModel() {
    }

    public Movie getMovie() {
        return mMovie;
    }

    public void setMovie(Movie movie) {
        mMovie = movie;
    }

    public String getBackdropPath() {
        return mMovie.getBackdropPath();
    }

    public double getVoteAverage(){
        return mMovie.getVoteAverage();
    }

    public String getTitle() {
        return mMovie.getTitle();
    }
}
