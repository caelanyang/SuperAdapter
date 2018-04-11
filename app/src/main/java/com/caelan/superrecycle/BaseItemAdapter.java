package com.caelan.superrecycle;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

/**
 * Created by yangjiacheng on 2018/4/11.
 * ...
 */
public class BaseItemAdapter implements ItemAdapter<Object, MyBindViewHolder> {


    @Override
    public void onBindViewHolder(MyBindViewHolder holder, Object o) {

    }

    @Override
    public MyBindViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }
}
