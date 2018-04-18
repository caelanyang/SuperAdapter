package com.caelan.superrecycle.ItemBinder;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.caelan.superadapter.ItemBinder;
import com.caelan.superadapter.SuperViewHolder;
import com.caelan.superrecycle.R;
import com.caelan.superrecycle.bean.TextBean;

/**
 * Created by yangjiacheng on 2018/4/18.
 * ...
 */
public class SimpleTextItemBinder extends ItemBinder<TextBean> {

    public SimpleTextItemBinder(int layoutId) {
        super(layoutId);
    }

    @Override
    public void onBindViewHolder(SuperViewHolder holder, TextBean textBean) {
        TextView textView = holder.get(R.id.simple_text);
        TextView remove = holder.get(R.id.remove);
        //registerClickListener(holder, remove);
        textView.setText(textBean.getText());
    }

    @Override
    public void onClick(View v, int position, TextBean textBean) {
        if (v.getId() == R.id.remove) {
            getSuperAdapter().getDataSource().removeData(position);
        } else {
            Toast.makeText(v.getContext(), textBean.getText(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onLongClick(View v, int position, TextBean textBean) {
        Toast.makeText(v.getContext(), textBean.getText() + "long click", Toast.LENGTH_SHORT).show();
    }

    @NonNull
    @Override
    public int[] getViewsIdRegisterClickListener() {
        return new int[]{R.id.remove};
    }

}
