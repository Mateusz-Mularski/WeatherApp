package com.example.WeatherApp;

import org.json.JSONException;

import java.util.concurrent.Callable;

/**
 * Created by Mateusz Mularski on 30.01.14.
 */
public class WeatherFinder implements Callable {

    String[] params;

    public WeatherFinder(String[] params){
        this.params=params;
    }

    @Override
    public WeatherData[] call(){

        String data = ( HttpClient.getWeatherJSON(params, HttpClient.Options.FIND_BY_NAME) );
        try {
            return WeatherParser.findCities(data);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
