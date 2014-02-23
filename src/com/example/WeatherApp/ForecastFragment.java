package com.example.WeatherApp;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Mateusz Mularski on 06.02.14.
 */
public class ForecastFragment extends Fragment {
    WeatherData data;
    public static Fragment newInstance(Context context, WeatherData data){
        ForecastFragment forecast = new ForecastFragment();
        forecast.data=data;
        return forecast;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.precisedayweather, null);


        TextView location = (TextView)root.findViewById(R.id.location);
        TextView date = (TextView)root.findViewById(R.id.date);
        ImageView image = (ImageView)root.findViewById(R.id.imageView);
        TextView temperature = (TextView)root.findViewById(R.id.temperature);
        TextView mainDescription = (TextView)root.findViewById(R.id.mainDesc);
        TextView description = (TextView)root.findViewById(R.id.description);
        TextView windSpeed = (TextView)root.findViewById(R.id.windSpeed);
        TextView windDir = (TextView)root.findViewById(R.id.windDir);
        TextView rain = (TextView)root.findViewById(R.id.rain);
        TextView snow = (TextView)root.findViewById(R.id.snow);
        TextView pressure = (TextView)root.findViewById(R.id.pressure);
        TextView cloudsPerc = (TextView)root.findViewById(R.id.clouds);

        location.setText(data.getLocation());

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(new Date().getTime());
        calendar.add(Calendar.DATE, data.getDaysFromNow());
        date.setText(new SimpleDateFormat("dd.MM.yyyy").format(calendar.getTime()));

        image.setImageResource(data.getImage());
        temperature.setText(data.getTemperature()+"'C");
        mainDescription.setText(data.getCondition());
        description.setText(data.getDescription());
        windSpeed.setText("Wind speed: "+data.getWindSpeed()+"km/h");
        windDir.setText("Wind direction: "+data.getWindDirection());
        rain.setText("Rain: "+data.getRainAmmount()+"mm");
        snow.setText("Snow: "+data.getSnowAmmount()+"mm");
        pressure.setText(data.getPressure()+"hPa");
        cloudsPerc.setText("Clouds: "+data.getCloudsPerc()+"%");


        return root;
    }
}
