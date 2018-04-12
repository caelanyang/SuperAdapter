package com.caelan.superrecycle;

import com.caelan.superadapter.BaseData;

/**
 * Created by yangjiacheng on 2018/4/12.
 * ...
 */
public class TextBean extends BaseData {

    public TextBean(String text) {
        generateIdentifier();
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    private String text;
}
