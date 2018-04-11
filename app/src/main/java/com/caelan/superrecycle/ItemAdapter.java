package com.caelan.superrecycle;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

/**
 * Created by yangjiacheng on 2018/4/11.
 * ...
 */
public interface ItemAdapter<DATA, VH extends RecyclerView.ViewHolder> {

    void onBindViewHolder(VH holder, DATA data);

    VH onCreateViewHolder(ViewGroup parent, int viewType);

}
