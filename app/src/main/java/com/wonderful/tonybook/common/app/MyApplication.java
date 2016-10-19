package com.wonderful.tonybook.common.app;

import android.app.Application;

/**
 * 全局类
 * Created by KXwon on 2016/10/19.
 */

public class MyApplication extends Application {

    private static MyApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }


    /**
     * Singleton main method. Provides the global static instance of the helper class.
     * @return The MyApplication instance.
     */
    public static synchronized MyApplication getInstance() {
        return mInstance;
    }

}
