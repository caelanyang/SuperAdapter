package com.caelan.superrecycle.tabfragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.caelan.superadapter.DefaultDataSource;
import com.caelan.superadapter.SuperAdapter;
import com.caelan.superrecycle.ItemBinder.HorizontalRecycleItemBinder;
import com.caelan.superrecycle.ItemBinder.SimpleImageItemBinder;
import com.caelan.superrecycle.ItemBinder.SimpleTextItemBinder;
import com.caelan.superrecycle.R;
import com.caelan.superrecycle.bean.HorizontalBean;
import com.caelan.superrecycle.bean.ImageBean;
import com.caelan.superrecycle.bean.TextBean;

import java.util.ArrayList;

/**
 * Created by yangjiacheng on 2018/4/18.
 * ...
 */
public class RedFragment extends Fragment implements View.OnClickListener {

    private DefaultDataSource<Object> dataSource;

    public RedFragment() {}

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.view_red, container, false);
        initView(rootView);
        initClickListener(rootView);
        return rootView;
    }

    private void initClickListener(View rootView) {
        rootView.findViewById(R.id.head_insert).setOnClickListener(this);
        rootView.findViewById(R.id.middle_insert).setOnClickListener(this);
        rootView.findViewById(R.id.end_insert).setOnClickListener(this);
    }

    private void initView(View rootView) {
        RecyclerView recyclerView = rootView.findViewById(R.id.recycle_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        SuperAdapter<Object> superAdapter = new SuperAdapter<>(getContext());
        recyclerView.setNestedScrollingEnabled(false);
        SimpleTextItemBinder simpleTextItemBinder = new SimpleTextItemBinder(R.layout.item_simple_text);
        SimpleImageItemBinder simpleImageItemBinder = new SimpleImageItemBinder(R.layout.item_simple_image);
        HorizontalRecycleItemBinder horizontalRecycleItemBinder = new HorizontalRecycleItemBinder(R.layout.item_horizontal_recycle_view);

        superAdapter.with(simpleTextItemBinder, simpleImageItemBinder, horizontalRecycleItemBinder);

        dataSource = new DefaultDataSource<>(obtainData(20), new DefaultDataSource.Intercept() {
            @Override
            public int onIntercept(int position, Object data) {
                if (data instanceof TextBean) {
                    return 0;
                } else if (data instanceof ImageBean) {
                    return 1;
                } else if (data instanceof HorizontalBean) {
                    return 2;
                }
                return 0;
            }
        });
        superAdapter.setDataSource(dataSource);
        recyclerView.setAdapter(superAdapter);
    }

    ArrayList<Object> obtainData(int range) {
        ArrayList<Object> arrayList = new ArrayList<>();
        for (int i = 0; i < range; i++) {
            if (i == 3) {
                HorizontalBean horizontalBean = new HorizontalBean();
                horizontalBean.setTextBeans(obtainHorizontalData(20, ""));
                arrayList.add(horizontalBean);
                continue;
            }
            TextBean textBean = new TextBean("I am " + i);
            Log.d("hasCode", String.valueOf(textBean.hashCode()));
            arrayList.add(textBean);
            ImageBean imageBean = new ImageBean(R.mipmap.ic_launcher_round);
            Log.d("hasCode", String.valueOf(imageBean.hashCode()));
            arrayList.add(imageBean);
        }
        return arrayList;
    }

    ArrayList<TextBean> obtainHorizontalData(int range, String optionalString) {
        ArrayList<TextBean> arrayList = new ArrayList<>();
        for (int i = 0; i < range; i++) {
            TextBean textBean = new TextBean("Horizontal " + optionalString + i);
            Log.d("hasCode", String.valueOf(textBean.hashCode()));
            arrayList.add(textBean);
        }
        return arrayList;
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.head_insert:
                dataSource.insertData(new TextBean("I am head insert"), 0);
                break;
            case R.id.middle_insert:
                dataSource.moveData(1, 3);
                break;
            case R.id.end_insert:
                //dataSource.setNewDataList(obtainData(2));
                HorizontalBean horizontalBean = new HorizontalBean();
                horizontalBean.setTextBeans(obtainHorizontalData(12, "payload"));
                dataSource.notifyItemChanged(6, horizontalBean);
                break;
            default:
                break;
        }
    }

}
