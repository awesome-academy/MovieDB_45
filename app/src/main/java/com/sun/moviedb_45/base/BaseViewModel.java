package com.sun.moviedb_45.base;

import android.arch.lifecycle.ViewModel;

import java.lang.ref.WeakReference;

import io.reactivex.disposables.CompositeDisposable;

public class BaseViewModel<V> extends ViewModel {

    private CompositeDisposable mCompositeDisposable;

    private WeakReference<V> mNavigator;

    public BaseViewModel() {
        this.mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    protected void onCleared() {
        mCompositeDisposable.dispose();
        super.onCleared();
    }

    public V getNavigator() {
        return mNavigator.get();
    }

    public void setNavigator(V navigator) {
        this.mNavigator = new WeakReference<>(navigator);
    }
}
