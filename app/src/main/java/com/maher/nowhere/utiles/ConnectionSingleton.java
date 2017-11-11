package com.maher.nowhere.utiles;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by Maher on 10/30/17.
 */

public class ConnectionSingleton {
    private static ConnectionSingleton mInstance;
    private static RequestQueue requestQueue;
    private static Context mCtx;

    private ConnectionSingleton(Context context) {
        mCtx = context;
        requestQueue = getRequestQueue();

    }


    private RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(mCtx.getApplicationContext());
        }
        return requestQueue;
    }

    public static synchronized ConnectionSingleton getInstance(Context context) {

        if (mInstance == null) {
            mInstance = new ConnectionSingleton(context);

        }
        return mInstance;
    }

    public <T> void addToRequestque(Request<T> request) {

        requestQueue.add(request);

    }
}
