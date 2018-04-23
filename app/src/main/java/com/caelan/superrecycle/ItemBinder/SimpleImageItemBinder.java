package com.caelan.superrecycle.ItemBinder;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.caelan.superadapter.ItemBinder;
import com.caelan.superadapter.SuperViewHolder;
import com.caelan.superrecycle.R;
import com.caelan.superrecycle.bean.ImageBean;

import java.util.List;

/**
 * Created by yangjiacheng on 2018/4/18.
 * ...
 */
public class SimpleImageItemBinder extends ItemBinder<ImageBean> {

    public SimpleImageItemBinder(int layoutId) {
        super(layoutId);
    }

    @Override
    public SuperViewHolder onCreateViewHolder(@NonNull ViewGroup parent, @NonNull LayoutInflater inflater) {
        SuperViewHolder superViewHolder = super.onCreateViewHolder(parent, inflater);
        superViewHolder.holderChildViewByIds(R.id.remove, R.id.simple_image);
        registerClickListener(superViewHolder, true, R.id.simple_image, R.id.remove);
        return superViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SuperViewHolder holder, ImageBean imageBean) {
        ImageView imageView = holder.get(R.id.simple_image);
        imageView.setImageResource(imageBean.getImageRes());
    }

    @Override
    public void onBindViewHolder(@NonNull SuperViewHolder holder, ImageBean imageBean, @NonNull List<Object> payloads) {
        if (payloads.isEmpty()) {
            super.onBindViewHolder(holder, imageBean, payloads);
        } else {
            if (payloads.get(0) instanceof Integer) {
                int i = (int) payloads.get(0);
                if (i == 1) {
                    ImageView imageView = holder.get(R.id.simple_image);
                    imageView.setImageResource((R.mipmap.ic_launcher));
                }
            }
        }
    }

    @Override
    public void onClick(View v, int position, ImageBean imageBean) {
        if (v.getId() == R.id.remove) {
            getSuperAdapter().getDataSource().removeData(position);
        } else {
            Toast.makeText(v.getContext(), imageBean.getImageRes(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onLongClick(View v, int position, ImageBean imageBean) {
        Toast.makeText(v.getContext(), position + "", Toast.LENGTH_SHORT).show();
        getSuperAdapter().notifyItemChanged(position, 1);
    }
}
