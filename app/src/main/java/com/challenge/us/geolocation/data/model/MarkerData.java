package com.challenge.us.geolocation.data.model;

import androidx.annotation.NonNull;

public class MarkerData {

    private String locationName;
    private double lat;
    private double lng;

    public MarkerData() {
    }

    public MarkerData(String locationName, double lat, double lng) {
        this.locationName = locationName;
        this.lat = lat;
        this.lng = lng;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    @NonNull
    @Override
    public String toString() {
        return "MarkerData{" +
                "locationName='" + locationName + '\'' +
                ", lat=" + lat +
                ", lng=" + lng +
                '}';
    }
}
