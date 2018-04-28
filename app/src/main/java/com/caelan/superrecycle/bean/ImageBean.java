package com.caelan.superrecycle.bean;

import android.support.annotation.DrawableRes;

/**
 * Created by yangjiacheng on 2018/4/12.
 * ...
 */
public class ImageBean {

    public ImageBean(int imageRes) {
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
