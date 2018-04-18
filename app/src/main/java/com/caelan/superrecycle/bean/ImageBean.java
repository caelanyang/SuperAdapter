package com.caelan.superrecycle.bean;

import android.support.annotation.DrawableRes;

import com.caelan.superadapter.BaseData;

/**
 * Created by yangjiacheng on 2018/4/12.
 * ...
 */
public class ImageBean extends BaseData {

    public ImageBean(int imageRes) {
        generateIdentifier();
        this.imageRes = imageRes;
    }

    public int getImageRes() {
        return imageRes;
    }

    public void setImageRes(int imageRes) {
        this.imageRes = imageRes;
    }

    @DrawableRes
    private int imageRes;
}
