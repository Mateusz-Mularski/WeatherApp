package com.example.WeatherApp;

import org.json.JSONException;

import java.util.concurrent.Callable;

/**
 * Created by Mateusz Mularski on 06.02.14.
 */
public class CurrWeatherGetter implements Callable {

    String[] loc;

    public CurrWeatherGetter(String[] loc){
        this.loc=loc;
    }


    @Override
    public WeatherData call(){
        String data;
        if(loc.length==1)
            data = ( HttpClient.getWeatherJSON(loc, HttpClient.Options.CURRENT_BY_ID));
        else if(loc.length==2)
            data = (HttpClient.getWeatherJSON(loc, HttpClient.Options.CURRENT_BY_GPSLOCATION));
        else
            data=null;

        try {
            return WeatherParser.getCurrentWeather(data);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return new WeatherData();
    }
}
