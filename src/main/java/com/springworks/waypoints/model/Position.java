package com.springworks.waypoints.model;

public class Position {
    private double longitude;
    private double latitude;

    public Position(){}

    public Position(double longitude, double latitude) {
        if (longitude < -180 || longitude > 180) {
            throw new IllegalArgumentException("Invalid longitude value.");
        }
        if (latitude < -90 || latitude > 90) {
            throw new IllegalArgumentException("Invalid latitude value.");
        }
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }
}
