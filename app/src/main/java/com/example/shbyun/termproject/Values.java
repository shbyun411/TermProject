package com.example.shbyun.termproject;

/**
 * Created by shbyun on 2016-12-01.


public class Values {

    private String weatherString = "";    ;
    private static Values instance;

    private Values(){

        //weatherString = "";
    }
    public static Values getInstance(){
        if(instance == null){
            instance = new Values();
        }

        return instance;

    }



    public void setWeather(String weatherStr){
        this.weatherString = weatherStr;
    }
    public String getWeather(){
        return weatherString;
    }

}

 */