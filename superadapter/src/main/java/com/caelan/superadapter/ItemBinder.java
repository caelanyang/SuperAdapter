package com.caelan.superadapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by yangjiacheng on 2018/4/12.
 * ...
 */
public abstract class ItemBinder<Model> implements ItemClickListener<Model> {

    private SuperAdapter mSuperAdapter;

    @LayoutRes
    private int layoutId;


    public ItemBinder(@LayoutRes int layoutId) {
        this.layoutId = layoutId;
    }

    public SuperViewHolder onCreateViewHolder(ViewGroup parent, LayoutInflater inflater) {
        View view = inflater.inflate(layoutId, parent, false);
        SuperViewHolder viewHolder = new SuperViewHolder(view);
        initClickListener(viewHolder, getViewsIdRegisterClickListener());
        return viewHolder;
    }

    private void initClickListener(SuperViewHolder viewHolder, int... ids) {
        ArrayList<View> views = new ArrayList<>();
        if (getItemClickListenerEnable()) {
            views.add(viewHolder.itemView);
        }
        for (int id : ids) {
            views.add(viewHolder.get(id));
        }
        View[] viewArray = new View[views.size()];
        registerClickListener(viewHolder, views.toArray(viewArray));
    }

    /**
     * 只控制整个Item的点击事件，默认注册
     *
     * @see #getItemClickListenerEnable()
     * note: 不控制子View的点击事件，子View通过
     * @see #getViewsIdRegisterClickListener() 或者
     * @see #registerClickListener(SuperViewHolder, View...) 注册
     */
    public boolean getItemClickListenerEnable() {
        return true;
    }

    @NonNull
    public int[] getViewsIdRegisterClickListener() {
        return new int[0];
    }

    @SuppressWarnings("unchecked")
    public void registerClickListener(final SuperViewHolder viewHolder, View... views) {
        final ItemBinder that = this;
        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = viewHolder.getAdapterPosition();
                that.onClick(v, position, mSuperAdapter.getDataSource().getData(position));
            }
        };
        View.OnLongClickListener longClickListener = new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                int position = viewHolder.getAdapterPosition();
                that.onLongClick(v, position, mSuperAdapter.getDataSource().getData(position));
                return true;
            }
        };
        for (View view : views) {
            view.setOnClickListener(clickListener);
            view.setOnLongClickListener(longClickListener);
        }

    }

    @Override
    public void onClick(View v, int position, Model model) {

    }

    @Override
    public void onLongClick(View v, int position, Model model) {

    }

    protected abstract void onBindViewHolder(SuperViewHolder holder, Model model);

    public void onViewRecycled(@NonNull SuperViewHolder holder) {

    }

    public boolean onFailedToRecycleView(@NonNull SuperViewHolder holder) {
        return false;
    }

    public void onViewAttachedToWindow(@NonNull SuperViewHolder holder) {

    }

    public void onViewDetachedFromWindow(@NonNull SuperViewHolder holder) {

    }

    public SuperAdapter getSuperAdapter() {
        return mSuperAdapter;
    }

    public void setSuperAdapter(@NonNull SuperAdapter superAdapter) {
        mSuperAdapter = superAdapter;
    }
}
