package com.caelan.superrecycle;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

/**
 * Created by yangjiacheng on 2018/4/11.
 * ...
 */
interface ItemAdapter<Model, VH extends RecyclerView.ViewHolder> {

    void onBindViewHolder(VH holder, Model model);

    VH onCreateViewHolder(ViewGroup parent, int viewType);
}
