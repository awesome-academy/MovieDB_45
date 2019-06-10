package com.sun.moviedb_45.ui.home;

import com.sun.moviedb_45.R;
import com.sun.moviedb_45.base.BaseFragment;
import com.sun.moviedb_45.databinding.FragmentHomeBinding;

public class HomeFragment extends BaseFragment<FragmentHomeBinding, HomeViewModel> {
    private static HomeFragment sInstance;

    public static HomeFragment getInstance() {
        if (sInstance == null) {
            sInstance = new HomeFragment();
        }
        return sInstance;
    }

    @Override
    protected HomeViewModel getViewModel() {
        return null;
    }

    @Override
    protected int getBindingVariable() {
        return 0;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_home;
    }
}
