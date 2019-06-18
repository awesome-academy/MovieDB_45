package com.sun.moviedb_45.ui.home;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableList;

import com.sun.moviedb_45.base.BaseViewModel;
import com.sun.moviedb_45.data.model.CategoryKey;
import com.sun.moviedb_45.data.model.CategoryName;
import com.sun.moviedb_45.data.model.Movie;
import com.sun.moviedb_45.data.source.MovieRepository;
import com.sun.moviedb_45.service.response.MovieResponse;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class HomeViewModel extends BaseViewModel<HomeNavigator> {
    private static int FIRST_PAGE = 1;
    private HomeNavigator mHomeNavigator;
    private MovieRepository mRepository;
    private CompositeDisposable mDisposable;

    public ObservableList<Movie> nowPlayingObservable;
    public ObservableList<Movie> upcomingObservable;
    public ObservableList<Movie> topRatedObservable;
    public ObservableList<Movie> popularMovieObservable;
    public ObservableList<String> categoriesNameObservable;
    public ObservableList<ObservableList<Movie>> categoriesMovieObservable;

    public ObservableBoolean isLoadMoreNowPlaying;
    public ObservableBoolean isLoadMoreUpComing;
    public ObservableBoolean isLoadMoreTopRated;
    public ObservableBoolean isLoadMorePopular;

    public HomeViewModel(MovieRepository repository) {
        mRepository = repository;
        mDisposable = new CompositeDisposable();
        nowPlayingObservable = new ObservableArrayList<>();
        upcomingObservable = new ObservableArrayList<>();
        topRatedObservable = new ObservableArrayList<>();
        popularMovieObservable = new ObservableArrayList<>();
        categoriesMovieObservable = new ObservableArrayList<>();
        categoriesNameObservable = new ObservableArrayList<>();

        isLoadMoreNowPlaying = new ObservableBoolean(false);
        isLoadMoreUpComing = new ObservableBoolean(false);
        isLoadMoreTopRated = new ObservableBoolean(false);
        isLoadMorePopular = new ObservableBoolean(false);
    }

    public void getData(){
        getDataNowPlayingMovie();

        getDataUpComingMovie();

        getDataTopRatedMovie();

        getDataPopularMovie();
    }

    private void getDataNowPlayingMovie() {
        Disposable disposable = mRepository.getMovieByCategory(CategoryKey.CATEGORY_NOW_PLAYING,
                FIRST_PAGE)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<MovieResponse>() {
                    @Override
                    public void accept(MovieResponse movieResponse) throws Exception {
                        nowPlayingObservable.addAll(movieResponse.getResults());
                        categoriesMovieObservable.add(nowPlayingObservable);
                        categoriesNameObservable.add(CategoryName.TITLE_NOW_PLAYING);
                        isLoadMoreNowPlaying.set(true);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        handleError(throwable.getMessage());
                    }
                });
        mDisposable.add(disposable);
    }

    private void handleError(String message) {
    }

    private void getDataUpComingMovie() {
        Disposable disposable = mRepository.getMovieByCategory(CategoryKey.CATEGORY_UP_COMING,
                FIRST_PAGE)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<MovieResponse>() {
                    @Override
                    public void accept(MovieResponse movieResponse) throws Exception {
                        upcomingObservable.addAll(movieResponse.getResults());
                        categoriesMovieObservable.add(upcomingObservable);
                        categoriesNameObservable.add(CategoryName.TITLE_UP_COMING);
                        isLoadMoreUpComing.set(true);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        handleError(throwable.getMessage());
                    }
                });
        mDisposable.add(disposable);
    }

    private void getDataTopRatedMovie() {
        Disposable disposable = mRepository.getMovieByCategory(CategoryKey.CATEGORY_TOP_RATE,
                FIRST_PAGE)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<MovieResponse>() {
                    @Override
                    public void accept(MovieResponse movieResponse) throws Exception {
                        topRatedObservable.addAll(movieResponse.getResults());
                        categoriesMovieObservable.add(topRatedObservable);
                        categoriesNameObservable.add(CategoryName.TITLE_TOP_RATE);
                        isLoadMoreTopRated.set(true);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        handleError(throwable.getMessage());
                    }
                });
        mDisposable.add(disposable);
    }

    private void getDataPopularMovie() {
        Disposable disposable = mRepository.getMovieByCategory(CategoryKey.CATEGORY_POPULAR,
                FIRST_PAGE)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<MovieResponse>() {
                    @Override
                    public void accept(MovieResponse movieResponse) throws Exception {
                        popularMovieObservable.addAll(movieResponse.getResults());
                        categoriesMovieObservable.add(popularMovieObservable);
                        categoriesNameObservable.add(CategoryName.TITLE_POPULAR);
                        isLoadMorePopular.set(true);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        handleError(throwable.getMessage());
                    }
                });
        mDisposable.add(disposable);
    }

    public void dispose() {
        mDisposable.dispose();
    }
}
