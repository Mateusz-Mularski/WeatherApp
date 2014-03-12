package com.example.WeatherApp;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;

/**
 * Created by Mateusz Mularski on 11.03.14.
 */
public class TabListener implements ActionBar.TabListener {

    private Fragment fragment;
    private Activity activity;
    private String tag;
    private boolean firstSelection=true;

    public TabListener(Activity activity, String tag, Fragment fragment){
        this.activity=activity;
        this.tag=tag;
        this.fragment=fragment;
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        if(firstSelection){
            fragmentTransaction.add(android.R.id.content, fragment, tag);
            firstSelection=false;
        } else {
            if(tab.getPosition()==1)
                ((ForecastPagerFragment)fragment)._adapter.notifyDataSetChanged();
            fragmentTransaction.attach(fragment);
        }
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        if(fragment!=null)
            fragmentTransaction.detach(fragment);
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

    }
}
