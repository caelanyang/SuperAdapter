package com.caelan.superadapter;

import java.io.Serializable;

/**
 * Created by yangjiacheng on 2018/4/12.
 * ...
 */
public abstract class BaseData implements Serializable {

    private static int count = 0;

    public void generateIdentifier() {
        identifier = count++;
    }

    public int getIdentifier() {
        return identifier;
    }

    private int identifier;


}
