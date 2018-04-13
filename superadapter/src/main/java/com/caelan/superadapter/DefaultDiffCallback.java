package com.caelan.superadapter;

import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;

import java.util.List;

/**
 * Created by yangjiacheng on 2018/4/12.
 * ...
 */
public class DefaultDiffCallback<Model> extends DiffUtil.Callback {

    private List<Model> oldDatas;
    private List<Model> newDatas;

    public DefaultDiffCallback(@NonNull List<Model> oldDatas, @NonNull List<Model> newDatas) {
        this.oldDatas = oldDatas;
        this.newDatas = newDatas;
    }

    @Override
    public int getOldListSize() {
        return oldDatas.size();
    }

    @Override
    public int getNewListSize() {
        return newDatas.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldDatas.get(oldItemPosition).hashCode()
                == newDatas.get(newItemPosition).hashCode();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return false;
    }
}
