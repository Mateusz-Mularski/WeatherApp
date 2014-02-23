package com.example.WeatherApp;

import android.widget.SearchView;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by Mateusz Mularski on 30.01.14.
 */
public class SearchListener implements SearchView.OnQueryTextListener {

    ObservableWeatherList weatherList;

    public SearchListener(ObservableWeatherList weatherList){
        this.weatherList=weatherList;
    }

    @Override
    public boolean onQueryTextChange(String newText){
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query){

        try {
                WeatherFinder weatherFinder=new WeatherFinder(new String[]{query});
                ExecutorService executor = Executors.newSingleThreadExecutor();
                Future<WeatherData[]> result = executor.submit(weatherFinder);

                weatherList.clear();
                for(WeatherData tmp : result.get())
                    weatherList.add(tmp);
                weatherList.notifyAllObservers();
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return true;
    }
}
