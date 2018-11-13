package model;


import java.util.ArrayList;
import java.util.List;

public class Subject {
    List<Observer> observers = new ArrayList<>();

    public void addObserver(Observer observer){
        observers.add(observer);
    }

    public void notifyObservers(String name, Integer outOf){
        for (Observer o:observers) {
            o.update(name, outOf);
        }
    }
}
