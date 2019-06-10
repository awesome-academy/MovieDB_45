package com.sun.moviedb_45.base;

import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseRecyclerViewAdapter<T, VH extends RecyclerView.ViewHolder>
        extends RecyclerView.Adapter<VH> {
    protected List<T> mData;
    protected ItemListener<T> mItemListener;

    public BaseRecyclerViewAdapter() {
        mData = new ArrayList<>();
    }

    public void setItemListener(ItemListener<T> listener) {
        if (listener != null)
            mItemListener = listener;
    }

    public List<T> getData() {
        return mData;
    }

    public void setData(List<T> data) {
        if (data != null) {
            mData.clear();
            mData.addAll(data);
            notifyDataSetChanged();
        }
    }

    public void addData(List<T> data) {
        if (data != null) {
            int startPosition = data.size();
            mData.addAll(data);
            notifyItemRangeChanged(startPosition, data.size() - 1);
        }
    }

    public void removeItem(int position) {
        mData.remove(position);
        notifyItemRemoved(position);
    }

    public interface ItemListener<T> {
        void onItemClick(T t, int position);
    }
}
