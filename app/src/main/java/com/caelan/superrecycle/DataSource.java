package com.caelan.superrecycle;

/**
 * Created by yangjiacheng on 2017/12/11.
 * ...
 */

public interface DataSource {

    int getDataType(int position);

    int getDataCount();

    Object getData(int position);

}
