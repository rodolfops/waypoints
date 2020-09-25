package com.springworks.waypoints.categoritazion;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springworks.waypoints.model.Result;
import com.springworks.waypoints.model.Waypoint;
import com.springworks.waypoints.parser.GenericParser;
import net.sf.geographiclib.Geodesic;
import net.sf.geographiclib.GeodesicLine;
import net.sf.geographiclib.GeodesicMask;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Categorizer {

    private static Geodesic geod = Geodesic.WGS84;

    private GenericParser genericParser;
    private List<Waypoint> points;
    private Result result;

    public Categorizer(){}

    public Categorizer(String filename){
        init( filename );
        processData();
    }

    private void processData() {
        if(this.points.size() == 1) {
            System.out.println("Waypoints: not enough points");
            return;
        }

        this.points = this.points.stream()
                .sorted( Comparator.comparing( Waypoint::getTimestamp ) )
                        .collect( Collectors.toList());
        this.result = new Result(calculateDistanceSpeeding(this.points),
                calculateDurationSpeeding(this.points),
                calculateTotalDistance(this.points.get(this.points.size()-1), this.points.get(0)),
                calculateTotalDuration(this.points.get(this.points.size()-1), this.points.get(0)));
    }

    private void init(String filename) {
        this.genericParser = new GenericParser();
        try {
            List<JsonNode> nodes = this.genericParser.parse(filename);
            this.points = new ObjectMapper().convertValue( nodes, new TypeReference<List<Waypoint>>(){} );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printResult() {
        System.out.println(this.result.toString());
    }

    public double calculateTotalDuration(Waypoint lastPoint, Waypoint firstPoint) {
        return (lastPoint.getTimestamp().toEpochSecond() - firstPoint.getTimestamp().toEpochSecond());
    }

    public double calculateTotalDistance(Waypoint lastPoint, Waypoint firstPoint) {
        return getDistance(lastPoint.getLatitude(), lastPoint.getLongitude(), firstPoint.getLatitude(), firstPoint.getLongitude());
    }

    public double calculateDurationSpeeding(List<Waypoint> waypointList) {
        return IntStream.range(1, waypointList.size() - 1)
            .mapToDouble( i -> {
                if(waypointList.get(i).getSpeed() > waypointList.get(i).getSpeedLimit()) {
                    return (waypointList.get(i).getTimestamp().toEpochSecond() - waypointList.get(i-1).getTimestamp().toEpochSecond());
                } else {
                    return 0;
                }
            }).sum();
    }

    public double calculateDistanceSpeeding(List<Waypoint> waypointList) {
        return IntStream.range(1, waypointList.size() - 1)
            .mapToDouble( i ->
            { if(waypointList.get(i).getSpeed() > waypointList.get(i).getSpeedLimit()) {
                return getDistance(waypointList.get(i).getLatitude(), waypointList.get(i).getLongitude(), waypointList.get(i-1).getLatitude(), waypointList.get(i-1).getLongitude());
            } else {
                return 0;
            }
            }).sum();
    }

    /**
     * Get the distance between two points in meters.
     * @param lat1 First point's latitude
     * @param lon1 First point's longitude
     * @param lat2 Second point's latitude
     * @param lon2 Second point's longitude
     * @return Distance between the first and the second point in meters
     */
    private double getDistance(double lat1, double lon1, double lat2, double lon2) {
        GeodesicLine line = geod.InverseLine(lat1, lon1, lat2, lon2, GeodesicMask.DISTANCE_IN | GeodesicMask.LATITUDE | GeodesicMask.LONGITUDE);
        return line.Distance();
    }
}
