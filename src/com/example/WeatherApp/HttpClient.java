package com.example.WeatherApp;

import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Mateusz Mularski on 30.01.14.
 */
public class HttpClient {

    public enum Options {
        FIND_BY_NAME,
        FORECAST_BY_ID,
        FORECAST_BY_GPSLOCATION,
        CURRENT_BY_ID,
        CURRENT_BY_GPSLOCATION
    }

    private static String FIND_URL = "http://api.openweathermap.org/data/2.5/find?q=";
    private static String CURR_URL = "http://api.openweathermap.org/data/2.5/weather?";
    private static String FORECAST_URL = "http://api.openweathermap.org/data/2.5/forecast/daily?";

    public static String getWeatherJSON(String[] location, Options op){

        HttpURLConnection con=null;
        InputStream input=null;
        String properUrl;

        switch (op){
            case FIND_BY_NAME:
                properUrl=FIND_URL + location[0] + "&type=like&units=metric&mode=json";
                break;
            case FORECAST_BY_GPSLOCATION:
                properUrl=FORECAST_URL + "lat=" + location[0] + "&lon=" + location[1] + "&units=metric&mode=json&cnt=14";
                break;
            case FORECAST_BY_ID:
                properUrl=FORECAST_URL + "id=" + location[0] + "&units=metric&mode=json&cnt=14";
                break;
            case CURRENT_BY_ID:
                properUrl=CURR_URL+"id="+location[0]+"&units=metric&mode=json";
                break;
            case CURRENT_BY_GPSLOCATION:
                properUrl=CURR_URL+"lat="+location[0]+"&lon="+location[1]+"&units=metric&mode=json";
                break;
            default:
                return null;
        }

        try {
            con = (HttpURLConnection) ( new URL(properUrl)).openConnection();
            con.setRequestMethod("GET");
            con.setDoInput(true);
            con.setDoOutput(true);
            con.connect();

            Log.d("uerel", con.getURL().toString());

            StringBuffer buffer = new StringBuffer();
            input = con.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(input));
            String line = null;
            while (  (line = br.readLine()) != null )
                buffer.append(line + "\r\n");

            input.close();
            con.disconnect();
            return buffer.toString();
        }
        catch(Throwable t){
            t.printStackTrace();
        }
        finally {
            try {input.close();}catch(Throwable t){}
            try {con.disconnect();}catch(Throwable t){}
        }
        return null;
    }
}
