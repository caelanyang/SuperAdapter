package com.caelan.superadapter;

import android.view.View;

/**
 * Created by yangjiacheng on 2018/4/12.
 * ...
 */
public interface ItemClickListener<Model> {

    void onClick(View v, int position, Model model);

    void onLongClick(View v, int position, Model model);
}
