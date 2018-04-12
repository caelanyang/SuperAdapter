package com.caelan.superrecycle.superadapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by yangjiacheng on 2018/4/12.
 * ...
 */
public abstract class ItemAdapter<Model> {

    private SuperAdapter mSuperAdapter;

    @LayoutRes
    private int layoutId;

    public ItemAdapter(@LayoutRes int layoutId) {
        this.layoutId = layoutId;
    }

    public SuperViewHolder onCreateViewHolder(ViewGroup parent, LayoutInflater inflater) {
        View view = inflater.inflate(layoutId, parent, false);
        return new SuperViewHolder(view);
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
