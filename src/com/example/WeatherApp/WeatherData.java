package com.example.WeatherApp;

/**
 * Created by Mateusz Mularski on 30.01.14.
 */
public class WeatherData {
    private String location;
    private String condition;
    private String description;
    private float temperature;
    private float windSpeed;
    private float windDeg;
    private float rainAmmount;
    private float snowAmmount;
    private int cloudsPerc;
    private float pressure;
    private float humidity;
    private int sunrise;
    private int sunset;
    private int image;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int id;

    private long curTime;

    private int daysFromNow;


    public int getImage(){
        return image;
    }

    public void setImageById(int id){
        if(id>=200&&id<300)
            image=R.drawable.thunderstorm;
        else if(id>=300&&id<400)
            image=R.drawable.drizzle;
        else if(id>=500&&id<600){
            switch(id){
                case 500:
                    image=R.drawable.drizzle;
                    break;
                default:
                    image=R.drawable.rain;
            }
        }
        else if(id>=600&&id<700)
            image=R.drawable.snow;
        else if(id>=700&&id<800)
            image=R.drawable.mist;
        else if(id>=800&&id<900)
            switch (id){
                case 800:
                    image=R.drawable.clear;
                    break;
                case 801:
                    image=R.drawable.fewclouds;
                    break;
                case 802:
                case 803:
                case 804:
                    image=R.drawable.overcast;
            }
    }


    public long getCurTime() {
        return curTime;
    }

    public void setCurTime(long curTime) {
        this.curTime = curTime;
    }

    public int getSunrise(){
        return sunrise;
    }

    public void setSunrise(int sunrise){
        this.sunrise=sunrise;
    }

    public int getSunset(){
        return sunset;
    }

    public void setSunset(int sunset){
        this.sunset=sunset;
    }

    public int getDaysFromNow(){
        return daysFromNow;
    }

    public void setDaysFromNow(int daysFromNow){
        this.daysFromNow=daysFromNow;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public float getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(float windSpeed) {
        this.windSpeed = windSpeed;
    }

    public float getWindDeg() {
        return windDeg;
    }

    public void setWindDeg(float windDeg) {
        this.windDeg = windDeg;
    }

    public String getWindDirection(){
        if (windDeg >= 0 && windDeg <= 11.25) {
            return "N";
        } else if (windDeg > 11.25 && windDeg <= 33.75) {
            return "NNE";
        } else if (windDeg > 33.75 && windDeg <= 56.25) {
            return "NE";
        } else if (windDeg > 56.25 && windDeg <= 78.75){
            return "ENE";
        } else if (windDeg > 78.75 && windDeg <= 101.25) {
            return "E";
        } else if (windDeg > 101.25 && windDeg <= 123.75) {
            return "ESE";
        } else if (windDeg > 123.75 && windDeg <= 146.25) {
            return "SE";
        } else if (windDeg > 146.25 && windDeg <= 168.75) {
            return "SSE";
        } else if (windDeg > 168.76 && windDeg <= 191.25) {
            return "S";
        } else if (windDeg > 191.25 && windDeg <= 213.75) {
            return "SSW";
        } else if (windDeg > 213.75 && windDeg <= 236.25) {
            return "SW";
        } else if (windDeg > 236.25 && windDeg <= 258.75) {
            return "WSW";
        } else if (windDeg > 258.75 && windDeg <= 281.25) {
            return "W";
        } else if (windDeg > 281.25 && windDeg <= 303.75) {
            return "WNW";
        } else if (windDeg > 303.75 && windDeg <= 326.25) {
            return "NW";
        } else if (windDeg > 326.25 && windDeg <= 348.75) {
            return "NNW";
        } else if(windDeg>348.75){
            return "N";
        }
        return null;
    }

    public float getRainAmmount() {
        return rainAmmount;
    }

    public void setRainAmmount(float rainAmmount) {
        this.rainAmmount = rainAmmount;
    }

    public float getSnowAmmount() {
        return snowAmmount;
    }

    public void setSnowAmmount(float snowAmmount) {
        this.snowAmmount = snowAmmount;
    }

    public int getCloudsPerc() {
        return cloudsPerc;
    }

    public void setCloudsPerc(int cloudsPerc) {
        this.cloudsPerc = cloudsPerc;
    }

    public float getPressure() {
        return pressure;
    }

    public void setPressure(float pressure) {
        this.pressure = pressure;
    }

    public float getHumidity() {
        return humidity;
    }

    public void setHumidity(float humidity) {
        this.humidity = humidity;
    }
}
