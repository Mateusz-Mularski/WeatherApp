package com.example.WeatherApp;

import android.app.Fragment;
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
 * Created by Mateusz Mularski on 11.03.14.
 */
public class CurrWeatherFragment extends Fragment {
    private WeatherData data;
    private ViewGroup container;
    private View view;

    public CurrWeatherFragment(WeatherData data){
        this.data=data;
    }

    @Override
    public void onCreate(Bundle saved){
        super.onCreate(saved);
    }

    /** Called when the activity is first created. */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.currweather, container, false);
        this.container=container;

        setUpView();
        return view;
    }

    private void setUpView(){
        TextView location = (TextView)view.findViewById(R.id.location);
        TextView date = (TextView)view.findViewById(R.id.date);
        ImageView image = (ImageView)view.findViewById(R.id.imageView);
        TextView mainTemp = (TextView)view.findViewById(R.id.temperature);
        TextView minTemp = (TextView)view.findViewById(R.id.tempMin);
        TextView maxTemp = (TextView)view.findViewById(R.id.tempMax);
        TextView mainDescription = (TextView)view.findViewById(R.id.mainDesc);
        TextView description = (TextView)view.findViewById(R.id.description);
        TextView windSpeed = (TextView)view.findViewById(R.id.windSpeed);
        TextView windDir = (TextView)view.findViewById(R.id.windDir);
        TextView rain = (TextView)view.findViewById(R.id.rain);
        TextView snow = (TextView)view.findViewById(R.id.snow);
        TextView pressure = (TextView)view.findViewById(R.id.pressure);
        TextView cloudsPerc = (TextView)view.findViewById(R.id.clouds);
        TextView humidity = (TextView)view.findViewById(R.id.humidity);
        TextView sunrise = (TextView)view.findViewById(R.id.sunrise);
        TextView sunset = (TextView)view.findViewById(R.id.sunset);

        location.setText(data.getLocation());

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(new Date().getTime());
        calendar.add(Calendar.DATE, data.getDaysFromNow());
        date.setText(new SimpleDateFormat("dd.MM.yyyy").format(calendar.getTime()));

        image.setImageResource(data.getImage());

        mainTemp.setText(data.getTemperature()+"\u00B0"+"C");
        minTemp.setText("Min: "+data.getTempMin()+"\u00B0"+"C");
        maxTemp.setText("Max: "+data.getTempMax()+"\u00B0"+"C");
        mainDescription.setText(data.getCondition());
        description.setText(data.getDescription());
        windSpeed.setText("Wind speed: "+data.getWindSpeed()+"km/h");
        windDir.setText("Wind direction: "+data.getWindDirection());
        rain.setText("Rain: "+data.getRainAmmount()+"mm");
        snow.setText("Snow: "+data.getSnowAmmount()+"mm");
        pressure.setText(data.getPressure()+"hPa");
        cloudsPerc.setText("Clouds: "+data.getCloudsPerc()+"%");
        humidity.setText("Humidity: "+data.getHumidity()+"%");


        sunrise.setText("Sunrise: "+convertToString(data.getSunrise()));
        sunset.setText("Sunset: "+convertToString(data.getSunset()));

    }

    private String convertToString(int tm){
        Date dt = new Date(tm);
        return dt.getHours()+":"+dt.getMinutes()+":"+dt.getSeconds();
    }
}
