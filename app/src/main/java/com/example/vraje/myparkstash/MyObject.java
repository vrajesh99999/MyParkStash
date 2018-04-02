package com.example.vraje.myparkstash;

/**
 * Created by vrajesh on 3/27/2018.
 */

class MyObject {
    public Integer id;
    public double longitute;
    public double latitute;

    @Override
    public String toString() {
        return "MyObject: id = " + id + "; longitute = " + longitute + "; latitute = " + latitute;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setlong(double aLong) {
        this.longitute = aLong;
    }

    public void setlat(double aLat) {
        this.latitute = aLat;
    }
}
