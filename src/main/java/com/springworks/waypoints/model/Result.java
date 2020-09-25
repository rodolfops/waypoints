package com.springworks.waypoints.model;

import java.text.DecimalFormat;

public class Result {

    private static DecimalFormat df2 = new DecimalFormat("#.##");

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
        return "Result {\n" +
                "   distanceSpeeding = " + df2.format(distanceSpeeding) + " m,\n" +
                "   durationSpeeding = " + durationSpeeding + " s,\n" +
                "   totalDistance = " + df2.format(totalDistance) + " m,\n" +
                "   totalDuration = " + totalDuration + " s,\n" +
                '}';
    }
}
