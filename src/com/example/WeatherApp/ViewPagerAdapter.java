package com.example.WeatherApp;

import android.content.Context;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

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
        ForecastFragment f = ForecastFragment.getInstance(forecastData.get(position));
        Log.d("forecastfragment", f.data.getDescription().toString());
        return f;
    }

    @Override
    public int getItemPosition(Object object){
        return POSITION_NONE;
    }

    @Override
    public int getCount() {
        return forecastData.size();
    }

    @Override
    public void restoreState(Parcelable par, ClassLoader loader){

    }
}