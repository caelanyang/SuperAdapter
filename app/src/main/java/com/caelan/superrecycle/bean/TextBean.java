package com.caelan.superrecycle.bean;

/**
 * Created by yangjiacheng on 2018/4/12.
 * ...
 */
public class TextBean {

    public TextBean(String text) {
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
