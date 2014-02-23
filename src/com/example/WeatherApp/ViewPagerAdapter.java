package com.example.WeatherApp;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.LinkedList;

public class ViewPagerAdapter extends FragmentPagerAdapter {
    private Context _context;
    private LinkedList<WeatherData> forecastData;

    public ViewPagerAdapter(Context context, FragmentManager fm, LinkedList<WeatherData> forecastData) {
        super(fm);
        _context=context;
        this.forecastData=forecastData;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment f = ForecastFragment.newInstance(_context, forecastData.get(position));
        return f;
    }

    @Override
    public int getCount() {
        return forecastData.size();
    }

}