package com.example.sunrise_sunset.sunrise_sunset;

public interface WorkerCommunicationCallback {

    void errorMessage(String error);

    void results(String[] res);
}
