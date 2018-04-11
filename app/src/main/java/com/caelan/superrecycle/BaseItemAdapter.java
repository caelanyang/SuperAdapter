package com.caelan.superrecycle;

import android.view.ViewGroup;

/**
 * Created by yangjiacheng on 2018/4/11.
 * ...
 */
public class BaseItemAdapter implements ItemAdapter<TextBean, SuperViewHolder> {


    @Override
    public void onBindViewHolder(SuperViewHolder holder, TextBean textBean) {

    }

    @Override
    public SuperViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }
}
