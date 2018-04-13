package com.caelan.superrecycle;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

/**
 * Created by yangjiacheng on 2018/4/13.
 * ...
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        initLeakCanary();
    }

    void initLeakCanary() {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this);
    }
}
