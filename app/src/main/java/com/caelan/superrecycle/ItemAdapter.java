package com.caelan.superrecycle;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

/**
 * Created by yangjiacheng on 2017/12/11.
 * ...
 */

public interface ItemAdapter<SuperViewHolder extends RecyclerView.ViewHolder, T> {

    SuperViewHolder onCreateViewHolder(ViewGroup parent, int viewType);

    void onBindViewHolder(SuperViewHolder viewHolder, T data);

}
