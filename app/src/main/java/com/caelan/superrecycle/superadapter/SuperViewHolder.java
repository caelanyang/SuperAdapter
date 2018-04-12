package com.caelan.superrecycle.superadapter;

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
        holderChildViewByIds(ids);
    }

    public void holderChildViewByIds(int... ids) {
        for (int id : ids) {
            viewHolder.put(id, itemView.findViewById(id));
        }
    }

    @SuppressWarnings("unchecked")
    public <T extends View> T get(int id) {
        View view = viewHolder.get(id);
        if (view == null) {
            view = itemView.findViewById(id);
            viewHolder.put(id, view);
        }
        return (T) view;
    }

    public View getRootView() {
        return itemView;
    }
}
