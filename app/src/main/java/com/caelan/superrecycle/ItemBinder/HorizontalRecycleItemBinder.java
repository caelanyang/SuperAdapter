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

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangjiacheng on 2018/4/18.
 * ...
 */
public class HorizontalRecycleItemBinder extends ItemBinder<HorizontalBean> {

    private SuperAdapter<TextBean> mAdapter;

    private boolean isFirstBind = true;

    private ItemBinder childItemBinder = new ItemBinder<TextBean>(R.layout.item_of_horizontal_recycle) {
        @Override
        public SuperViewHolder onCreateViewHolder(ViewGroup parent, LayoutInflater inflater) {
            SuperViewHolder superViewHolder = super.onCreateViewHolder(parent, inflater);
            superViewHolder.holderChildViewByIds(R.id.image_name, R.id.simple_image);
            registerClickListener(superViewHolder, true);
            return superViewHolder;
        }

        @Override
        public void onBindViewHolder(SuperViewHolder holder, TextBean textBean) {
            TextView imageName = holder.get(R.id.image_name);
            imageName.setText(textBean.getText());
        }

        @Override
        public void onBindViewHolder(@NonNull SuperViewHolder holder, TextBean textBean, @NonNull List<Object> payloads) {
            if (payloads.isEmpty()) {
                super.onBindViewHolder(holder, textBean, payloads);
            } else {
                if (payloads.get(0) instanceof String) {
                    TextView imageName = holder.get(R.id.image_name);
                    String s = textBean.getText() + payloads.get(0);
                    imageName.setText(s);
                }
            }
        }

        @Override
        public void onClick(View v, int position, TextBean textBean) {
            Toast.makeText(v.getContext(), textBean.getText(), Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onLongClick(View v, int position, TextBean textBean) {
            Toast.makeText(v.getContext(), textBean.getText() + "Long", Toast.LENGTH_SHORT).show();
            getSuperAdapter().notifyItemChanged(position, "payload");
        }
    };

    public HorizontalRecycleItemBinder(int layoutId) {
        super(layoutId);
    }

    private void initView(SuperViewHolder viewHolder) {
        Context context = viewHolder.itemView.getContext();
        RecyclerView horizontalView = viewHolder.get(R.id.horizontal_recycle_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        horizontalView.setItemAnimator(new DefaultItemAnimator());
        horizontalView.setLayoutManager(layoutManager);
        mAdapter = new SuperAdapter<>(context);
        mAdapter.with(childItemBinder);
        DataSource<TextBean> horizontalDataSource = new DefaultDataSource<>(new ArrayList<TextBean>());
        mAdapter.setDataSource(horizontalDataSource);
        horizontalView.setAdapter(mAdapter);
    }

    @Override
    public SuperViewHolder onCreateViewHolder(@NonNull ViewGroup parent, @NonNull LayoutInflater inflater) {
        SuperViewHolder superViewHolder = super.onCreateViewHolder(parent, inflater);
        initView(superViewHolder);
        registerClickListener(superViewHolder, false);
        return superViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SuperViewHolder holder, HorizontalBean horizontalBean) {
        if (isFirstBind) {
            mAdapter.getDataSource().setDataList(horizontalBean.getTextBeans());
            isFirstBind = false;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull SuperViewHolder holder, HorizontalBean horizontalBean, @NonNull List<Object> payloads) {
        if (payloads.isEmpty()) {
            super.onBindViewHolder(holder, horizontalBean, payloads);
        } else {
            if (payloads.get(0) instanceof HorizontalBean) {
                HorizontalBean horizontalBean1 = (HorizontalBean) payloads.get(0);
                mAdapter.getDataSource().setDataList(horizontalBean1.getTextBeans());
            }
        }
    }

}
