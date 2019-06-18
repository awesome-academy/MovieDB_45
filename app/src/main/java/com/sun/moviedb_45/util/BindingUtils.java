package com.sun.moviedb_45.util;

import android.databinding.BindingAdapter;
import android.databinding.ObservableList;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.sun.moviedb_45.R;
import com.sun.moviedb_45.data.model.Movie;
import com.sun.moviedb_45.ui.home.HomeCategoryAdapter;
import com.sun.moviedb_45.ui.home.ItemCategoryAdapter;

public class BindingUtils {
    @BindingAdapter("imageUrl")
    public static void setImage(ImageView imageView, String image_url) {
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.drawable.loading);
        requestOptions.error(R.drawable.loading);
        String imageLink = StringUtils.getImage(image_url);
        Glide.with(imageView.getContext())
                .load(imageLink)
                .apply(requestOptions)
                .into(imageView);
    }
    @BindingAdapter("bindCategory")
    public static void bindCategory(RecyclerView recyclerView, ObservableList<Movie> movies){
        ItemCategoryAdapter adapter = (ItemCategoryAdapter) recyclerView.getAdapter();
        if (adapter != null){
            adapter.update(movies);
        }
    }

    @BindingAdapter(value = {"bindCategoryMovie", "bindCategoryTitle"}, requireAll = false)
    public static void bindRecyclerCategories(RecyclerView recycler,
                                              ObservableList<ObservableList<Movie>> movies,
                                              ObservableList<String> categories) {
        HomeCategoryAdapter adapter = (HomeCategoryAdapter) recycler.getAdapter();
        if (adapter != null) {
            adapter.update(movies, categories);
        }
    }
}
