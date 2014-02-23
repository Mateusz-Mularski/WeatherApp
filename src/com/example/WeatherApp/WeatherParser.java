package com.example.WeatherApp;

import android.util.Log;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Mateusz Mularski on 30.01.14.
 */
public class WeatherParser {

    //ViewPager
    public static WeatherData[] findCities(String data) throws JSONException {

        if(data==null){
            return new WeatherData[]{new WeatherData()};
        }
        Log.d("weatherdatastring", data);


        JSONObject jObj1 =new JSONObject(data);
        JSONArray main =jObj1.getJSONArray("list");
        int arraySize=main.length();

        WeatherData[] weather=new WeatherData[arraySize];
        for(int i=0; i<arraySize; i++)
            weather[i]=new WeatherData();

        for(int i=0; i<arraySize; i++){
            JSONObject jObj = main.getJSONObject(i);


            // We start extracting the info

            JSONObject sysObj = jObj.getJSONObject("sys");
            weather[i].setId(jObj.getInt("id"));
            weather[i].setLocation(jObj.getString("name")+","+sysObj.getString("country"));

            //weather.setSunrise(sysObj.getInt("sunrise"));
            //weather.setSunset(sysObj.getInt("sunset"));

            // We get weather info (This is an array)
            JSONArray jArr = jObj.getJSONArray("weather");

            // We use only the first value
            JSONObject moreInfo = jArr.getJSONObject(0);
            weather[i].setImageById(moreInfo.getInt("id"));
            weather[i].setCondition(moreInfo.getString("main"));
            weather[i].setDescription(moreInfo.getString("description"));


            JSONObject mainObj = jObj.getJSONObject("main");
            weather[i].setHumidity(mainObj.getInt("humidity"));
            weather[i].setPressure(mainObj.getInt("pressure"));
            weather[i].setTemperature((float) mainObj.getDouble("temp"));


            weather[i].setCurTime(jObj.getLong("dt"));

            // Wind
            JSONObject wObj = jObj.getJSONObject("wind");
            weather[i].setWindSpeed((float) wObj.getDouble("speed"));
            weather[i].setWindDeg((float) wObj.getDouble("deg"));

            // Clouds
            weather[i].setCloudsPerc(jObj.getJSONObject("clouds").getInt("all"));
            Log.d("PARSER", "przeszlo");
        }

        return weather;
    }

    public static WeatherData[] getDailyForecast(String data) throws JSONException {
        if(data==null){
            return new WeatherData[]{new WeatherData()};
        }
        Log.d("weatherdatastring", data);


        JSONObject jObj1 =new JSONObject(data);
        JSONArray main =jObj1.getJSONArray("list");
        int arraySize=main.length();

        WeatherData[] weather=new WeatherData[arraySize];
        for(int i=0; i<arraySize; i++){
            weather[i]=new WeatherData();
            weather[i].setLocation(jObj1.getJSONObject("city").getString("name")+","+
                                    jObj1.getJSONObject("city").getString("country"));
        }

        for(int i=0; i<arraySize; i++){
            JSONObject jObj = main.getJSONObject(i);

            weather[i].setDaysFromNow(i);
            // We start extracting the info

            // We get weather info (This is an array)
            JSONObject temp = jObj.getJSONObject("temp");
            weather[i].setTemperature((float)temp.getDouble("day"));

            // We use only the first value
            JSONArray jArr = jObj.getJSONArray("weather");
            JSONObject moreInfo = jArr.getJSONObject(0);
            weather[i].setImageById(moreInfo.getInt("id"));
            weather[i].setCondition(moreInfo.getString("main"));
            weather[i].setDescription(moreInfo.getString("description"));


            weather[i].setHumidity(jObj.getInt("humidity"));
            weather[i].setPressure(jObj.getInt("pressure"));
            weather[i].setWindSpeed((float)jObj.getDouble("speed"));
            weather[i].setWindDeg((float)jObj.getDouble("deg"));
            weather[i].setCloudsPerc(jObj.getInt("clouds"));
            if(!jObj.isNull("rain"))
                weather[i].setRainAmmount((float)jObj.getDouble("rain"));
            if(!jObj.isNull("snow"))
                weather[i].setSnowAmmount((float)jObj.getDouble("snow"));

            weather[i].setCurTime(jObj.getLong("dt"));

            Log.d("PARSER", "przeszlo");
        }

        return weather;
    }
}
