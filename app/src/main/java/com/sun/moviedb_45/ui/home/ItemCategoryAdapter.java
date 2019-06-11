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
import com.sun.moviedb_45.databinding.ItemCategoryMovieBinding;

public class ItemCategoryAdapter extends RecyclerView.Adapter<ItemCategoryAdapter.ViewHolder> {
    private ObservableList<Movie> mMovies;
    private MovieListener mListener;

    public ItemCategoryAdapter(MovieListener listener) {
        mListener = listener;
        mMovies = new ObservableArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        ItemCategoryMovieBinding binding = DataBindingUtil.inflate(inflater,
                R.layout.item_category_movie, viewGroup, false);
        return new ViewHolder(binding, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.bindData(mMovies.get(i));
    }

    @Override
    public int getItemCount() {
        return mMovies == null ? 0 : mMovies.size();
    }

    public void update(ObservableList<Movie> movies) {
        mMovies = movies;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private  ItemCategoryMovieBinding mMovieBinding;
        private MovieListener mListener;
        public ViewHolder(ItemCategoryMovieBinding binding, MovieListener listener) {
            super(binding.getRoot());
            mMovieBinding = binding;
            mListener = listener;
        }

        @Override
        public void onClick(View v) {
            if (mListener != null){
                mListener.onMovieClick(mMovieBinding.getViewModel().getMovie());
            }
        }

        public void bindData(Movie movie) {
            mMovieBinding.setViewModel(new MovieViewModel());
            mMovieBinding.getViewModel().setMovie(movie);
            mMovieBinding.itemMovie.setOnClickListener(this);
            mMovieBinding.executePendingBindings();
        }
    }

    public interface MovieListener{
        void onMovieClick(Movie movie);
    }
}
