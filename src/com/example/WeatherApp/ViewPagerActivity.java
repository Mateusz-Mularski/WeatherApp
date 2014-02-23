package com.example.WeatherApp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by Mateusz Mularski on 06.02.14.
 */
public class ViewPagerActivity extends FragmentActivity {
    private ViewPager _mViewPager;
    private ViewPagerAdapter _adapter;
    private LinkedList<WeatherData> forecastData;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewpager);
        Intent i=getIntent();
        String[] location = i.getStringArrayExtra("loc");
        forecastData=new LinkedList<WeatherData>();

        ForecastGetter forecastGetter=new ForecastGetter(location);
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<WeatherData[]> result = executor.submit(forecastGetter);

        forecastData.clear();
        try{
            for(WeatherData tmp : result.get())
                forecastData.add(tmp);
        }
        catch(Exception e){
            e.printStackTrace();
        }

        setUpView();
    }


    private void setUpView(){
        _mViewPager = (ViewPager) findViewById(R.id.viewPager);
        _mViewPager.setPageMargin(25);
        _adapter = new ViewPagerAdapter(getApplicationContext(),getSupportFragmentManager(), forecastData);
        _mViewPager.setAdapter(_adapter);
        _mViewPager.setCurrentItem(0);
        _mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener(){

            @Override
            public void onPageScrollStateChanged(int position) {}
            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {}
            @Override
            public void onPageSelected(int position) {}

        });
    }
}
