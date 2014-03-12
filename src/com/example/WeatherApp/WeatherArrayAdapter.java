package com.example.WeatherApp;

/**
 * Created by Mateusz Mularski on 05.02.14.
 */

        import android.app.Activity;
        import android.content.Context;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ArrayAdapter;
        import android.widget.ImageView;
        import android.widget.TextView;

        import java.text.SimpleDateFormat;
        import java.util.LinkedList;

public class WeatherArrayAdapter extends ArrayAdapter<WeatherData> {
    private final Context context;
    private final LinkedList<WeatherData> values;

    public WeatherArrayAdapter(Context context) {
        super(context, R.layout.rowlayout, ((MainActivity)context).getWeatherList());
        this.context = context;
        this.values = ((MainActivity)context).getWeatherList();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView==null){
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            convertView = inflater.inflate(R.layout.rowlayout, parent, false);
        }

        TextView location = (TextView) convertView.findViewById(R.id.labelBig);
        TextView timeTemp = (TextView) convertView.findViewById(R.id.labelSmall);
        location.setText(values.get(position).getLocation());
        timeTemp.setText(new SimpleDateFormat("dd.MM.yyyy HH:mm").format(values.get(position).getCurTime()*1000L)+
                            ", "+values.get(position).getTemperature()+"\u00B0"+"C");
        ImageView image = (ImageView) convertView.findViewById(R.id.icon);
        image.setImageResource(values.get(position).getImage());

        return convertView;
    }

}
