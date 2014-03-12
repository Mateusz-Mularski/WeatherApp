package com.example.WeatherApp;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.LinkedList;

/**
 * Created by Mateusz Mularski on 06.02.14.
 */
public class ForecastPagerFragment extends Fragment {
    private ViewPager _mViewPager;
    public ViewPagerAdapter _adapter;
    private LinkedList<WeatherData> forecastData;
    private String[] tabs = {"Currently", "Forecast"};
    private ViewGroup container;
    private FragmentManager fragmentManager;
    private View view;
    private LayoutInflater inflater;
    private Context context;

    public ForecastPagerFragment(LinkedList<WeatherData> forecastData, FragmentManager fm, Context context){
        this.forecastData=forecastData;
        this.fragmentManager=fm;
        this.context=context;
    }

    /** Called when the fragment is first created. */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //this.inflater=inflater;

        view = inflater.inflate(R.layout.viewpager, container, false);
        this.container=container;

        setUpView();
        return view;
    }

    private void setUpView(){
        _mViewPager = (ViewPager) view.findViewById(R.id.viewPager);
        _mViewPager.setPageMargin(50);
        _adapter = new ViewPagerAdapter(view.getContext(), fragmentManager, forecastData);
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
