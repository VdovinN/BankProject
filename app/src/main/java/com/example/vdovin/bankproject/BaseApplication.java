package com.example.vdovin.bankproject;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

/**
 * Created by vdovin on 7/26/16.
 */
public class BaseApplication extends Application {

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

}
