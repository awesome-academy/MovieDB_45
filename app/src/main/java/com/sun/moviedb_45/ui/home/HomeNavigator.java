package com.sun.moviedb_45.ui.home;

import com.sun.moviedb_45.data.model.Movie;

public interface HomeNavigator {
    void showMovie(String categoryKey, String categoryName);
    void showMovieDetail(Movie movie);
}
