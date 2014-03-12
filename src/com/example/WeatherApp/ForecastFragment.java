package com.example.WeatherApp;

import android.support.v4.app.Fragment;
import android.os.Bundle;
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

    public static ForecastFragment getInstance(WeatherData weatherData){
        ForecastFragment ff=new ForecastFragment();
        ff.data=weatherData;
        return ff;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.forecastweather, null);


        TextView location = (TextView)root.findViewById(R.id.location);
        TextView date = (TextView)root.findViewById(R.id.date);
        ImageView image = (ImageView)root.findViewById(R.id.imageView);
        ImageView imageNight = (ImageView)root.findViewById(R.id.imageView2);
        TextView tempDay = (TextView)root.findViewById(R.id.tempDay);
        TextView tempNight = (TextView)root.findViewById(R.id.tempNight);
        TextView mainDescriptionDay = (TextView)root.findViewById(R.id.mainDescDay);
        TextView descriptionDay = (TextView)root.findViewById(R.id.descriptionDay);
        TextView mainDescriptionNight = (TextView)root.findViewById(R.id.mainDescNight);
        TextView descriptionNight = (TextView)root.findViewById(R.id.descNight);
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
        imageNight.setImageResource(data.getNightImage());

        tempDay.setText(data.getTemperature()+"\u00B0"+"C");
        tempNight.setText(data.getNightTemp()+"\u00B0"+"C");
        mainDescriptionDay.setText(data.getCondition());
        descriptionDay.setText(data.getDescription());
        mainDescriptionNight.setText(data.getCondition());
        descriptionNight.setText(data.getDescription());
        windSpeed.setText("Wind speed: "+data.getWindSpeed()+"km/h");
        windDir.setText("Wind direction: "+data.getWindDirection());
        rain.setText("Rain: "+data.getRainAmmount()+"mm");
        snow.setText("Snow: "+data.getSnowAmmount()+"mm");
        pressure.setText(data.getPressure()+"hPa");
        cloudsPerc.setText("Clouds: "+data.getCloudsPerc()+"%");


        return root;
    }
}
