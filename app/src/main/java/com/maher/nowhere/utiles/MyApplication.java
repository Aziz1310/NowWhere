package com.maher.nowhere.utiles;

/**
 * Created by Lincoln on 14/10/15.
 */

import android.app.Application;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.maher.nowhere.helpers.MyPreferenceManager;

public class MyApplication extends Application {

    public static final String TAG = MyApplication.class
            .getSimpleName();


    private MyPreferenceManager pref;

    private static MyApplication mInstance;



    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        Fresco.initialize(this);
    }

    public static synchronized MyApplication getInstance() {
        return mInstance;
    }

    public MyPreferenceManager getPrefManager() {
        if (pref == null) {
            pref = new MyPreferenceManager(this);
        }

        return pref;
    }

}
