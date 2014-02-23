package com.example.WeatherApp;

import org.json.JSONException;

import java.util.concurrent.Callable;

/**
 * Created by Mateusz Mularski on 06.02.14.
 */
public class ForecastGetter implements Callable {

        String[] loc;

        public ForecastGetter(String[] loc){
            this.loc=loc;
        }


        @Override
        public WeatherData[] call(){
            String data;
            if(loc.length==1)
                data = ( HttpClient.getWeatherJSON(loc, HttpClient.Options.FORECAST_BY_ID));
            else if(loc.length==2)
                data = (HttpClient.getWeatherJSON(loc, HttpClient.Options.FORECAST_BY_GPSLOCATION));
            else
                data=null;

            try {
                return WeatherParser.getDailyForecast(data);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return new WeatherData[]{new WeatherData()};
        }
}
