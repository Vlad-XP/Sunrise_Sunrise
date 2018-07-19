package com.example.sunrise_sunset.sunrise_sunset;

public class URLs {
    // part one, part two is the input from user (name of city) for google location url
    public static final String GL1_API_URL = "https://maps.googleapis.com/maps/api/place/findplacefromtext/json?input=";
    // part three for google location url
    public static final String GL3_INPUT_TYPE = "&inputtype=textquery";
    // part four for google location url
    public static final String GL4_REQUEST_FIELDS = "&fields=geometry";
    // part five for google location url
    public static final String GL5_AK = "&key=API_KEY_GOES_HERE";

    // end example: https://maps.googleapis.com/maps/api/place/findplacefromtext/json?input=Kiev&inputtype=textquery&fields=geometry&key=API_KEY_GOES_HERE

    // part one for sunrise-sunset.org api
    public static final String SS1_API_URL = "https://api.sunrise-sunset.org/json?";
    // part two follow by part three, value of latitude (ex. 11.832283)  for sunrise-sunset.org api
    public static final String SS2_LAT = "lat=";
    // part four follow by part five, value of longitude (ex. 22.512262) for sunrise-sunset.org api
    public static final String SS4_LNG = "&lng=";
    // part six for sunrise-sunset.org api
    public static final String SS6_FOMR = "&formatted=0";

    // end example: https://api.sunrise-sunset.org/json?lat=11.832283&lng=22.512262&date=2018-07-16&formatted=0
}
