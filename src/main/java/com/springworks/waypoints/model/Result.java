package com.springworks.waypoints.model;

public class Result {

    private double distanceSpeeding;
    private double durationSpeeding;
    private double totalDistance;
    private double totalDuration;

    public Result(double distanceSpeeding, double durationSpeeding, double totalDistance, double totalDuration) {
        this.distanceSpeeding = distanceSpeeding;
        this.durationSpeeding = durationSpeeding;
        this.totalDistance = totalDistance;
        this.totalDuration = totalDuration;
    }

    @Override
    public String toString() {
        return "Result{" +
                "distanceSpeeding=" + distanceSpeeding + " m" +
                ", durationSpeeding=" + durationSpeeding + " s" +
                ", totalDistance=" + totalDistance + " m" +
                ", totalDuration=" + totalDuration + " s" +
                '}';
    }
}
