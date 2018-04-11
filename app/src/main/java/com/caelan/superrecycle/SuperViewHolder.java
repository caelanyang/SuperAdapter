package com.caelan.superrecycle;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;

/**
 * Created by yangjiacheng on 2018/4/11.
 * ...
 */
public class SuperViewHolder extends RecyclerView.ViewHolder {

    private SparseArray<View> viewHolder = new SparseArray<>();

    public SuperViewHolder(View itemView) {
        super(itemView);
    }

    public SuperViewHolder(View itemView, int... ids) {
        super(itemView);
        for (int id : ids) {
            viewHolder.put(id, itemView.findViewById(id));
        }
    }

    public <T extends View> T get(int id) {
        View view = viewHolder.get(id);
        if (view == null) {
            view = itemView.findViewById(id);
            viewHolder.put(id, view);
        }
        return (T) view;
    }

    public <T> T get(int id, Class<T> c) {
        return (T) viewHolder.get(id);
    }
}
