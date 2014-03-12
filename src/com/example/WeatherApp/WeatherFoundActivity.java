package com.example.WeatherApp;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by Mateusz Mularski on 11.03.14.
 */
public class WeatherFoundActivity extends FragmentActivity {
    private ViewPager _mViewPager;
    private ViewPagerAdapter _adapter;
    private LinkedList<WeatherData> forecastData;
    private WeatherData currentWeatherData;
    private String[] tabs = {"Currently", "Forecast"};

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weatherfound);
        Intent i=getIntent();
        String[] location = i.getStringArrayExtra("loc");
        forecastData=new LinkedList<WeatherData>();


        CurrWeatherGetter weatherGetter=new CurrWeatherGetter(location);
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<WeatherData> result = executor.submit(weatherGetter);

        ForecastGetter forecastGetter=new ForecastGetter(location);
        ExecutorService executor2 = Executors.newSingleThreadExecutor();
        Future<WeatherData[]> result2 = executor2.submit(forecastGetter);

        forecastData.clear();
        try{
            for(WeatherData tmp : result2.get())
                forecastData.add(tmp);
            currentWeatherData=result.get();
        }
        catch(Exception e){
            e.printStackTrace();
        }

        CurrWeatherFragment currFragment=new CurrWeatherFragment(currentWeatherData);
        ForecastPagerFragment forecastFragment = new ForecastPagerFragment(forecastData, getSupportFragmentManager(), getApplicationContext());

        // get action bar
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        actionBar.addTab(actionBar.newTab().setText(tabs[0]).setTabListener(new TabListener(this, tabs[0], currFragment)));
        actionBar.addTab(actionBar.newTab().setText(tabs[1]).setTabListener(new TabListener(this, tabs[1], forecastFragment)));

        forceTabs();
    }

    // forcing tabs to be inside ActionBar
    private void forceTabs() {
        try {
            final ActionBar actionBar = getActionBar();
            final Method setHasEmbeddedTabsMethod = actionBar.getClass()
                    .getDeclaredMethod("setHasEmbeddedTabs", boolean.class);
            setHasEmbeddedTabsMethod.setAccessible(true);
            setHasEmbeddedTabsMethod.invoke(actionBar, true);
        }
        catch(final Exception e) {
            e.printStackTrace();
        }
    }
}
