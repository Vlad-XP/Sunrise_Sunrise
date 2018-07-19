package com.example.sunrise_sunset.sunrise_sunset;

import org.json.JSONException;
import org.json.JSONObject;

public class JsonParser {

    public String[] parseLocation(JSONObject jsonObject) throws JSONException, ErrorHandler.ErrorMessage {

        if (jsonObject.getString("status").equals("OK")) {
            JSONObject jo = (JSONObject) jsonObject.getJSONArray("candidates").get(0);
            jo = jo.getJSONObject("geometry").getJSONObject("location");
            return new String[]{jo.getString("lat"), jo.getString("lng")};
        } else {
            throw new ErrorHandler.ErrorMessage(jsonObject.getString("status"));
        }
    }

    public String[] parseSSTime(JSONObject jsonObject) throws JSONException {
        JSONObject tempJObj = jsonObject.getJSONObject("results");
        return new String[]{tempJObj.getString("sunrise"), tempJObj.getString("sunset")};
    }
}
