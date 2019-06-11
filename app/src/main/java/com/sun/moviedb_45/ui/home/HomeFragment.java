package com.sun.moviedb_45.ui.home;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.sun.moviedb_45.BR;
import com.sun.moviedb_45.R;
import com.sun.moviedb_45.base.BaseFragment;
import com.sun.moviedb_45.data.model.CategoryKey;
import com.sun.moviedb_45.data.model.CategoryName;
import com.sun.moviedb_45.data.model.Movie;
import com.sun.moviedb_45.data.source.MovieRepository;
import com.sun.moviedb_45.data.source.local.MovieLocalDataSource;
import com.sun.moviedb_45.data.source.remote.MovieRemoteDataSource;
import com.sun.moviedb_45.databinding.FragmentHomeBinding;

public class HomeFragment extends BaseFragment<FragmentHomeBinding, HomeViewModel>
        implements HomeNavigator, HomeCategoryAdapter.CategoryListener ,View.OnClickListener {
    private HomeViewModel mHomeViewModel;
    private FragmentHomeBinding mFragmentHomeBinding;

    public static HomeFragment getInstance() {
        HomeFragment frament = new HomeFragment();
        return frament;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHomeViewModel.setNavigator(this);
        mHomeViewModel.getData();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mFragmentHomeBinding = getViewDataBinding();
        initAdapter();
    }

    @Override
    public void onDestroy() {
        mHomeViewModel.dispose();
        super.onDestroy();
    }

    private void initAdapter() {
        mFragmentHomeBinding.recyclerHome.setAdapter(new HomeCategoryAdapter(this));
        mFragmentHomeBinding.recyclerHome.setNestedScrollingEnabled(false);
    }

    @Override
    protected HomeViewModel getViewModel() {
        if (mHomeViewModel == null){
            mHomeViewModel = new HomeViewModel(MovieRepository.getInstance(
                    MovieRemoteDataSource.getInstance(getActivity()),
                    MovieLocalDataSource.getInstance(getActivity())
            ));
        }
        return mHomeViewModel;
    }

    @Override
    protected int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_home;
    }

    @Override
    public void showMovie(String categoryKey, String categoryName) {

    }

    @Override
    public void showMovieDetail(Movie movie) {

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onCategoryClick(String category) {
        switch (category){
            case CategoryName.TITLE_NOW_PLAYING:
                showMovie(CategoryKey.CATEGORY_NOW_PLAYING,CategoryName.TITLE_NOW_PLAYING);
            case CategoryName.TITLE_POPULAR:
                showMovie(CategoryKey.CATEGORY_POPULAR,CategoryName.TITLE_POPULAR);
            case CategoryName.TITLE_TOP_RATE:
                showMovie(CategoryKey.CATEGORY_TOP_RATE,CategoryName.TITLE_TOP_RATE);
            case CategoryName.TITLE_UP_COMING:
                showMovie(CategoryKey.CATEGORY_UP_COMING,CategoryName.TITLE_UP_COMING);
        }
    }

    @Override
    public void onMovieClick(Movie movie) {
        showMovieDetail(movie);
    }
}
