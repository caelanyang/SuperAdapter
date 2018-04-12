package com.caelan.superadapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by yangjiacheng on 2018/4/12.
 * ...
 */
public abstract class ItemAdapter<Model> implements ItemClickListener<Model>, View.OnClickListener, View.OnLongClickListener {

    private SuperAdapter mSuperAdapter;

    private boolean clickListenerEnable = true;

    @LayoutRes
    private int layoutId;


    public ItemAdapter(@LayoutRes int layoutId) {
        this.layoutId = layoutId;
    }

    public SuperViewHolder onCreateViewHolder(ViewGroup parent, LayoutInflater inflater) {
        View view = inflater.inflate(layoutId, parent, false);
        SuperViewHolder viewHolder = new SuperViewHolder(view);
        assembleClickListener(viewHolder, viewHolder.itemView);
        return viewHolder;
    }

    @SuppressWarnings("unchecked")
    public void assembleClickListener(final SuperViewHolder viewHolder, View... views) {
        if (!clickListenerEnable) {
            return;
        }
        final ItemAdapter that = this;
        for (View view : views) {
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = viewHolder.getAdapterPosition();
                    that.onClick(v, position, mSuperAdapter.getDataSource().getData(position));
                }
            });
            view.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int position = viewHolder.getAdapterPosition();
                    that.onLongClick(v, position, mSuperAdapter.getDataSource().getData(position));
                    return true;
                }
            });
        }

    }

    @SuppressWarnings("unchecked")
    @Override
    public void onClick(View v) {

    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean onLongClick(View v) {
        return false;
    }

    @Override
    public void onClick(View v, int position, Model model) {

    }

    @Override
    public void onLongClick(View v, int position, Model model) {

    }

    protected abstract void onBindViewHolder(SuperViewHolder holder, Model model);

    public void onViewRecycled(@NonNull SuperViewHolder holder) {

    }

    public boolean onFailedToRecycleView(@NonNull SuperViewHolder holder) {
        return false;
    }

    public void onViewAttachedToWindow(@NonNull SuperViewHolder holder) {

    }

    public void onViewDetachedFromWindow(@NonNull SuperViewHolder holder) {

    }

    public SuperAdapter getSuperAdapter() {
        return mSuperAdapter;
    }

    public void setSuperAdapter(@NonNull SuperAdapter superAdapter) {
        mSuperAdapter = superAdapter;
    }
}
