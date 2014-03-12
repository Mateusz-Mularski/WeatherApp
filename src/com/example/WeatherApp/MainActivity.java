package com.example.WeatherApp;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.*;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by Mateusz Mularski
 */

public class MainActivity extends Activity implements Observer {
    ObservableWeatherList weatherList = new ObservableWeatherList();

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        weatherList.addObserver(this);

        ListView lv = (ListView) findViewById(R.id.listView);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String id = weatherList.get(i).getId()+"";

                Intent newIntent = new Intent(MainActivity.this, WeatherFoundActivity.class);
                newIntent.putExtra("loc", new String[]{id});
                startActivity(newIntent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_main_actions, menu);

        SearchManager searchManager= (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView sv = (SearchView) menu.findItem(R.id.action_search).getActionView();
        sv.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        sv.setOnQueryTextListener(new SearchListener(weatherList));

        MenuItem location = menu.findItem(R.id.action_location_found);
        location.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                Intent intent = new Intent("android.location.GPS_ENABLED_CHANGE");
                intent.putExtra("enabled", true);
                sendBroadcast(intent);
                LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                Criteria criteria = new Criteria();
                String provider=locationManager.getBestProvider(criteria, false);
                Location location = locationManager.getLastKnownLocation(provider);
                String longitude, latitude;
                if(location!=null){
                    longitude=location.getLongitude()+"";
                    latitude=location.getLatitude()+"";
                    Intent newIntent = new Intent(MainActivity.this, WeatherFoundActivity.class);
                    newIntent.putExtra("loc", new String[]{latitude, longitude});
                    startActivity(newIntent);
                }
                else
                    Toast.makeText(getBaseContext(), "GPS is disabled", 1000).show();
                return true;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onDestroy(){
        Intent intent = new Intent("android.location.GPS_ENABLED_CHANGE");
        intent.putExtra("enabled", false);
        sendBroadcast(intent);
        super.onDestroy();
    }

    @Override
    public void onPause(){
        Intent intent = new Intent("android.location.GPS_ENABLED_CHANGE");
        intent.putExtra("enabled", false);
        sendBroadcast(intent);
        super.onPause();
    }


    public ObservableWeatherList getWeatherList(){
        return weatherList;
    }

    @Override
    public void update(Observable o, Object arg) {

        InputMethodManager inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);

        ListView list=(ListView)findViewById(R.id.listView);
        list.setAdapter(new WeatherArrayAdapter(this));
    }
}
