package com.example.sunrise_sunset.sunrise_sunset;

import android.app.Application;

public class AppConnectivity extends Application{
    private static AppConnectivity mInstance;

    @Override
    public void onCreate() {
        super.onCreate();

        mInstance = this;
    }

    public static synchronized AppConnectivity getInstance() {
        return mInstance;
    }

    public void setConnectionListener(ConnectionReceiver.ConnectionReceiverListener listener) {
        ConnectionReceiver.connectionReceiverListener = listener;
    }
}
