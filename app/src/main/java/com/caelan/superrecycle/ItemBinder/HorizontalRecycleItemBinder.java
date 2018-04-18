package com.caelan.superrecycle.ItemBinder;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.caelan.superadapter.DataSource;
import com.caelan.superadapter.DefaultDataSource;
import com.caelan.superadapter.ItemBinder;
import com.caelan.superadapter.SuperAdapter;
import com.caelan.superadapter.SuperViewHolder;
import com.caelan.superrecycle.R;
import com.caelan.superrecycle.bean.HorizontalBean;
import com.caelan.superrecycle.bean.TextBean;

/**
 * Created by yangjiacheng on 2018/4/18.
 * ...
 */
public class HorizontalRecycleItemBinder extends ItemBinder<HorizontalBean> {

    private SuperAdapter mAdapter;

    private LinearLayoutManager mLayoutManager;

    private ItemBinder<TextBean> childItemBinder = new ItemBinder<TextBean>(R.layout.item_of_horizontal_recycle) {
        @Override
        protected void onBindViewHolder(SuperViewHolder holder, TextBean textBean) {
            TextView imageName = holder.get(R.id.image_name);
            imageName.setText(textBean.getText());
        }

        @Override
        public void onClick(View v, int position, TextBean textBean) {
            Toast.makeText(v.getContext(), textBean.getText(), Toast.LENGTH_SHORT).show();
        }

        @NonNull
        @Override
        public int[] getViewsIdRegisterClickListener() {
            return new int[]{R.id.simple_image};
        }
    };

    public HorizontalRecycleItemBinder(int layoutId) {
        super(layoutId);
    }

    @Override
    public SuperViewHolder onCreateViewHolder(ViewGroup parent, LayoutInflater inflater) {
        SuperViewHolder superViewHolder = super.onCreateViewHolder(parent, inflater);
        superViewHolder.holderChildViewByIds(R.id.horizontal_recycle_view);
        return superViewHolder;
    }

    @Override
    protected void onBindViewHolder(SuperViewHolder holder, HorizontalBean horizontalBean) {
        Context context = holder.itemView.getContext();
        RecyclerView horizontalView = holder.get(R.id.horizontal_recycle_view);
        if (mLayoutManager == null) {
            mLayoutManager = new LinearLayoutManager(context);
            mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            horizontalView.setItemAnimator(new DefaultItemAnimator());
            horizontalView.setLayoutManager(mLayoutManager);
        }
        if (mAdapter == null) {
            mAdapter = new SuperAdapter(context);
            mAdapter.with(childItemBinder);
            DataSource<TextBean> horizontalDataSource = new DefaultDataSource<>(horizontalBean.getTextBeans());
            mAdapter.setDataSource(horizontalDataSource);
            horizontalView.setAdapter(mAdapter);
        }
    }

    @Override
    public boolean getItemClickListenerEnable() {
        return false;
    }

}
