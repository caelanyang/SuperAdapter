package com.caelan.superrecycle.ItemBinder;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.caelan.superadapter.ItemBinder;
import com.caelan.superadapter.SuperViewHolder;
import com.caelan.superrecycle.R;
import com.caelan.superrecycle.bean.TextBean;

import java.util.List;

/**
 * Created by yangjiacheng on 2018/4/18.
 * ...
 */
public class SimpleTextItemBinder extends ItemBinder<TextBean> {

    public SimpleTextItemBinder(int layoutId) {
        super(layoutId);
    }

    @Override
    public SuperViewHolder onCreateViewHolder(ViewGroup parent, LayoutInflater inflater) {
        SuperViewHolder superViewHolder = super.onCreateViewHolder(parent, inflater);
        superViewHolder.holderChildViewByIds(R.id.simple_text, R.id.remove);
        registerClickListener(superViewHolder, true, R.id.simple_text, R.id.remove);
        return superViewHolder;
    }

    @Override
    public void onBindViewHolder(SuperViewHolder holder, TextBean textBean) {
        TextView textView = holder.get(R.id.simple_text);
        textView.setText(textBean.getText());
    }

    @Override
    public void onBindViewHolder(@NonNull SuperViewHolder holder, TextBean textBean, @NonNull List<Object> payloads) {
        if (payloads.isEmpty()) {
            super.onBindViewHolder(holder, textBean, payloads);
        } else {
            if (payloads.get(0) instanceof String) {
                TextView imageName = holder.get(R.id.simple_text);
                String s = textBean.getText() + payloads.get(0);
                imageName.setText(s);
            }
        }
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
        getSuperAdapter().notifyItemChanged(position, "payload");
    }
}
