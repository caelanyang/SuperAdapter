package com.caelan.superrecycle.superadapter;

import android.support.annotation.NonNull;

/**
 * Created by yangjiacheng on 2018/4/12.
 * ...
 */
public abstract class DefaultDataSource implements DataSource {

    private SuperAdapter superAdapter;

    public void setSuperAdapter(@NonNull SuperAdapter fastAdapter) {
        this.superAdapter = fastAdapter;
    }

    public SuperAdapter getSuperAdapter() {
        return superAdapter;
    }

}
