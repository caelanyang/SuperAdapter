package com.caelan.superrecycle;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;

/**
 * Created by yangjiacheng on 2017/12/12.
 * ...
 */

public class SuperViewHolder extends RecyclerView.ViewHolder {

    private SparseArray<View> views = new SparseArray<>();

    public SuperViewHolder(View itemView) {
        super(itemView);
    }

    public View get(int id) {
        View view = views.get(id);
        if (view == null) {
            view = itemView.findViewById(id);
            views.put(id, view);
        }
        return views.get(id);
    }

    public View getItemView() {
        return itemView;
    }
}
