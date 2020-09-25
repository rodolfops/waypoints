package com.springworks.waypoints.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.ZonedDateTime;

public class Waypoint {

    private ZonedDateTime timestamp;
    private Position position;
    private Double speed;
    @JsonProperty("speed_limit")
    private Double speedLimit;

    public Waypoint(){}

    public Waypoint(ZonedDateTime timestamp, Position position, double speed, double speedLimit) {
        if(timestamp.isAfter( ZonedDateTime.now() )){
            throw new IllegalArgumentException("Invalid future date.");
        }
        if(position == null) {
            throw new IllegalArgumentException("Invalid null position.");
        }
        if (speed < 0) {
            throw new IllegalArgumentException("Invalid negative speed.");
        }
        if (speedLimit < 0) {
            throw new IllegalArgumentException("Invalid negative speed limit.");
        }
        this.timestamp = timestamp;
        this.position = position;
        this.speed = speed;
        this.speedLimit = speedLimit;
    }

    public ZonedDateTime getTimestamp() {
        return timestamp;
    }

    public double getSpeed() {
        return speed;
    }

    public double getSpeedLimit() {
        return speedLimit;
    }

    public double getLongitude() {
        return this.position.getLongitude();
    }

    public double getLatitude() {
        return this.position.getLatitude();
    }

    public void setTimestamp(ZonedDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = ZonedDateTime.parse( timestamp );
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    public void setSpeedLimit(Double speedLimit) {
        this.speedLimit = speedLimit;
    }
}
