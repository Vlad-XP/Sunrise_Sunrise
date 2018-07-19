package com.example.sunrise_sunset.sunrise_sunset;

public class ErrorHandler {

    public static class ErrorMessage extends Exception{
        private static String mEM;
        ErrorMessage(String message){
            super(message);
            mEM = message;
        }

        public static String getEM() {
            return mEM;
        }
    }

}
