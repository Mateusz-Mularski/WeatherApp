package com.example.WeatherApp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
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

        SearchView sv = (SearchView) findViewById(R.id.searchView);
        int id = sv.getContext().getResources().getIdentifier("android:id/search_src_text", null, null);
        TextView textView = (TextView) sv.findViewById(id);
        textView.setTextColor(Color.WHITE);
        sv.setOnQueryTextListener(new SearchListener(weatherList));

        Button searchByGpsButton = (Button) findViewById(R.id.button);
        searchByGpsButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                if(location!=null){
                    Intent newIntent = new Intent(MainActivity.this, ViewPagerActivity.class);
                    newIntent.putExtra("loc", new String[]{""+location.getLatitude(), ""+location.getLongitude()});
                    startActivity(newIntent);f
                }
                else
                    Toast.makeText(getBaseContext(), "GPS is disabled", 1000).show();
            }
        });

        ListView lv = (ListView) findViewById(R.id.listView);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String id = weatherList.get(i).getId()+"";

                Intent newIntent = new Intent(MainActivity.this, ViewPagerActivity.class);
                newIntent.putExtra("loc", new String[]{id});
                startActivity(newIntent);
            }
        });
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
