package com.caelan.superadapter;

import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;

import java.util.List;

/**
 * Created by yangjiacheng on 2018/4/12.
 * ...
 */
public class CommonDiffCallback extends DiffUtil.Callback {

    private List<BaseData> oldItems;
    private List<BaseData> newItems;

    public CommonDiffCallback(@NonNull List<BaseData> oldItems, @NonNull List<BaseData> newItems) {
        this.oldItems = oldItems;
        this.newItems = newItems;
    }

    @Override
    public int getOldListSize() {
        return oldItems.size();
    }

    @Override
    public int getNewListSize() {
        return newItems.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldItems.get(oldItemPosition).getIdentifier()
                == newItems.get(newItemPosition).getIdentifier();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return false;
    }
}
