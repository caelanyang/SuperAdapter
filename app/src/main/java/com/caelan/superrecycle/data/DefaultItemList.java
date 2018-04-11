package com.caelan.superrecycle.data;

import com.caelan.superrecycle.SuperAdapter;

/**
 * Created by yangjiacheng on 2018/4/12.
 * ...
 */
public abstract class DefaultItemList implements DataSource {

    private SuperAdapter superAdapter;

    public void setSuperAdapter(SuperAdapter fastAdapter) {
        this.superAdapter = fastAdapter;
    }

    public SuperAdapter getSuperAdapter() {
        return superAdapter;
    }

}
