package com.caelan.superrecycle.bean;

import java.util.ArrayList;

/**
 * Created by yangjiacheng on 2018/4/18.
 * ...
 */
public class HorizontalBean {

    public ArrayList<TextBean> getTextBeans() {
        return mTextBeans;
    }

    public void setTextBeans(ArrayList<TextBean> textBeans) {
        mTextBeans = textBeans;
    }

    private ArrayList<TextBean> mTextBeans;

}
