package com.example.WeatherApp;

import java.util.LinkedList;
import java.util.Observer;

/**
 * Created by Mateusz Mularski on 22.02.14.
 */
public class ObservableWeatherList extends LinkedList<WeatherData> {
    LinkedList<Observer> observers;

    public ObservableWeatherList() {
        this.observers = new LinkedList<Observer>();
    }

    public void addData(WeatherData data){
        super.add(data);
        notifyAllObservers();
    }

    public void addMany(WeatherData[] data){
        for(WeatherData tmp : data)
            super.add(tmp);
        notifyAllObservers();
    }

    public void addObserver(Observer obs){
        observers.add(obs);
    }

    public void removeLastObserver() {
        observers.removeLast();
    }

    public void removeObserver(Observer obs){
        observers.remove(obs);
    }

    public void notifyAllObservers(){
        for(Observer tmp : observers)
            tmp.update(null, null);
    }
}
