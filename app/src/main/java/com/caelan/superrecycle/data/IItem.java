package com.caelan.superrecycle.data;

import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by yangjiacheng on 2018/4/12.
 * ...
 */
public interface IItem<VH extends RecyclerView.ViewHolder> {

    @IdRes
    int getType();

    @LayoutRes
    int getLayoutRes();

    VH getViewHolder(ViewGroup parent);

    void bindView(VH holder, List<Object> payloads);

    void unbindView(VH holder);

    void attachToWindow(VH holder);

    void detachFromWindow(VH holder);

    boolean failedToRecycle(VH holder);

}
