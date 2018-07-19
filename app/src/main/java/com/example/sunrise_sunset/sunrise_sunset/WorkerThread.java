package com.example.sunrise_sunset.sunrise_sunset;

import org.json.JSONException;
import org.json.JSONObject;

public class WorkerThread extends Thread {

    private WorkerCommunicationCallback mCallback;
    private String mInput;

    public WorkerThread(String userInput, WorkerCommunicationCallback wcc) {
        this.mInput = userInput;
        this.mCallback = wcc;
    }

    @Override
    public void run() {
        super.run();

        Network network = new Network();
        JsonParser jsonParser = new JsonParser();
        try {
            JSONObject jObj = network.getJsonObj(URLs.GL1_API_URL + mInput.replaceAll("\\s", "") +
                    URLs.GL3_INPUT_TYPE + URLs.GL4_REQUEST_FIELDS + URLs.GL5_AK);
            String[] loc = jsonParser.parseLocation(jObj);

            jObj = network.getJsonObj(URLs.SS1_API_URL + URLs.SS2_LAT + loc[0] + URLs.SS4_LNG + loc[1] + URLs.SS6_FOMR);
            String[] ssTime = jsonParser.parseSSTime(jObj);
            mCallback.results(ssTime);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (ErrorHandler.ErrorMessage errorMessage) {
            mCallback.errorMessage(errorMessage.getEM());
        }
    }
}
