package com.caelan.superrecycle.ItemBinder;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.caelan.superadapter.ItemBinder;
import com.caelan.superadapter.SuperViewHolder;
import com.caelan.superrecycle.R;
import com.caelan.superrecycle.bean.ImageBean;

/**
 * Created by yangjiacheng on 2018/4/18.
 * ...
 */
public class SimpleImageItemBinder extends ItemBinder<ImageBean> {

    public SimpleImageItemBinder(int layoutId) {
        super(layoutId);
    }

    @Override
    public void onBindViewHolder(SuperViewHolder holder, ImageBean imageBean) {
        ImageView imageView = holder.get(R.id.simple_image);
        imageView.setImageResource(imageBean.getImageRes());
    }

    @Override
    public void onClick(View v, int position, ImageBean imageBean) {
        if (v.getId() == R.id.remove) {
            getSuperAdapter().getDataSource().removeData(position);
        } else {
            Toast.makeText(v.getContext(), imageBean.getImageRes(), Toast.LENGTH_SHORT).show();
        }
    }

    @NonNull
    @Override
    public int[] getViewsIdRegisterClickListener() {
        return new int[]{R.id.remove};
    }

    @Override
    public boolean getItemClickListenerEnable() {
        return false;
    }
}
