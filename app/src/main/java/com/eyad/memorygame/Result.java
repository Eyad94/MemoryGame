package com.eyad.memorygame;

import android.support.annotation.NonNull;

public class Result implements Comparable<Result>{
    private String 	_id;
    private String 	_name;
    private  int _matches;
    private String _latitude;
    private String _longitude;

    public Result(String id, String name, String latitude, String longitude, int matches) {
        _id = id;
        _name = name;
        _matches = matches;
        _latitude = latitude;
        _longitude = longitude;
    }

    public String getId() {
        return _id;
    }

    public void setId (String id) {
        _id = id;
    }

    public String getName() {
        return _name;
    }

    public void setName(String name) {
        _name = name;
    }

    public int getMatches() {
        return _matches;
    }

    public void setMatches(int matches) {
        _matches = matches;
    }

    public String getLatitude() {
        return _latitude;
    }

    public void setLatitude(String latitude) {
        _latitude = latitude;
    }

    public String getLongitude() {
        return _longitude;
    }

    public void setLongitude(String longitude) {
        _longitude = longitude;
    }

    @Override
    public int compareTo(@NonNull Result another) {
        if (this.getMatches() < another.getMatches()){
            return 1;
        }else{
            return -1;
        }
    }
}
