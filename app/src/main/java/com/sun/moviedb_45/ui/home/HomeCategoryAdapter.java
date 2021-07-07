package com.sun.moviedb_45.ui.home;

import android.databinding.DataBindingUtil;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sun.moviedb_45.R;
import com.sun.moviedb_45.data.model.Movie;
import com.sun.moviedb_45.databinding.ItemHomeBinding;

public class HomeCategoryAdapter extends RecyclerView.Adapter<HomeCategoryAdapter.ViewHolder> {
    private ObservableList<ObservableList<Movie>> mMovies;
    private ObservableList<String> mCategory;
    private CategoryListener mListener;

    public HomeCategoryAdapter(CategoryListener listener) {
        mListener = listener;
        mMovies = new ObservableArrayList<>();
        mCategory = new ObservableArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        ItemHomeBinding binding = DataBindingUtil.inflate(layoutInflater,
                R.layout.item_home, viewGroup, false);

        return new ViewHolder(binding, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.bindData(mMovies.get(i), mCategory.get(i));
    }
    public void update(ObservableList<ObservableList<Movie>> movies,
                       ObservableList<String> categories){
        mMovies = movies;
        mCategory = categories;
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        return mMovies == null ? 0 : mMovies.size();
    }

    public interface CategoryListener {
        void onCategoryClick(String category);

        void onMovieClick(Movie movie);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,
            ItemCategoryAdapter.MovieListener {
        private ItemHomeBinding mItemHomeBinding;
        private CategoryListener mListener;

        public ViewHolder(ItemHomeBinding binding, CategoryListener listener) {
            super(binding.getRoot());
            mItemHomeBinding = binding;
            mListener = listener;
            mItemHomeBinding.recyclerCategory.setAdapter(new ItemCategoryAdapter(this));
            mItemHomeBinding.recyclerCategory.setNestedScrollingEnabled(true);
            mItemHomeBinding.textCategory.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mListener != null) {
                mListener.onCategoryClick(mItemHomeBinding.textCategory.getText().toString());
            }
        }

        public void bindData(ObservableList<Movie> movies, String category) {
            mItemHomeBinding.textCategory.setText(category);
            mItemHomeBinding.setCategoriesMovie(movies);
        }

        @Override
        public void onMovieClick(Movie movie) {
            mListener.onMovieClick(movie);
        }
    }
}
