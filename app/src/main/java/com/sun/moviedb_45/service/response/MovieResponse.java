package com.sun.moviedb_45.service.response;

import com.google.gson.annotations.SerializedName;
import com.sun.moviedb_45.data.model.Movie;

import java.util.List;

public class MovieResponse {
    @SerializedName("page")
    private int mPage;

    @SerializedName("total_page")
    private int mTotalPage;

    @SerializedName("total_results")
    private int mTotalResult;

    @SerializedName("results")
    private List<Movie> mMovies;

    public MovieResponse() {
    }

    public int getPage() {
        return mPage;
    }

    public void setPage(int page) {
        mPage = page;
    }

    public int getTotalPage() {
        return mTotalPage;
    }

    public void setTotalPage(int totalPage) {
        mTotalPage = totalPage;
    }

    public int getTotalResult() {
        return mTotalResult;
    }

    public void setTotalResult(int totalResult) {
        mTotalResult = totalResult;
    }

    public List<Movie> getResults() {
        return mMovies;
    }

    public void setResults(List<Movie> movies) {
        mMovies = movies;
    }
}
