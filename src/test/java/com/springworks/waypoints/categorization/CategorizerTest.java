package com.springworks.waypoints.categorization;

import com.springworks.waypoints.categoritazion.Categorizer;
import com.springworks.waypoints.model.Position;
import com.springworks.waypoints.model.Waypoint;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CategorizerTest {

    private Categorizer categorizer = new Categorizer();

    @Test
    public void totalDurationTest(){
        ZonedDateTime now = ZonedDateTime.now();
        Waypoint firstPoint = new Waypoint( now.minusSeconds(20), new Position( 18.0667,59.334), 6.3889, 8.33);
        Waypoint lastPoint = new Waypoint( now, new Position( 18.0666,59.3323), 8.33, 8.33);

        double total = categorizer.calculateTotalDuration( lastPoint,firstPoint );
        assertEquals(20, total, 0.01);
    }

    @Test
    public void totalDurationSamePointsTest(){
        ZonedDateTime now = ZonedDateTime.now();
        Waypoint firstPoint = new Waypoint( now, new Position( 18.0666,59.3323), 8.33, 8.33);
        Waypoint lastPoint = new Waypoint( now, new Position( 18.0666,59.3323), 8.33, 8.33);

        double total = categorizer.calculateTotalDuration( lastPoint,firstPoint );
        assertEquals(0, total, 0.01);
    }

    @Test
    public void totalDistanceTest(){
        ZonedDateTime now = ZonedDateTime.now();
        Waypoint firstPoint = new Waypoint( now.minusSeconds(20), new Position( 18.0667,59.334), 6.3889, 8.33);
        Waypoint lastPoint = new Waypoint( now, new Position( 18.0666,59.3323), 8.33, 8.33);

        double total = categorizer.calculateTotalDistance( lastPoint,firstPoint );
        assertEquals(189.0, total, 0.5);
    }

    @Test
    public void totalDistanceSamePointsTest(){
        ZonedDateTime now = ZonedDateTime.now();
        Waypoint firstPoint = new Waypoint( now, new Position( 18.0666,59.3323), 8.33, 8.33);
        Waypoint lastPoint = new Waypoint( now, new Position( 18.0666,59.3323), 8.33, 8.33);

        double total = categorizer.calculateTotalDistance( lastPoint,firstPoint );
        assertEquals(0, total, 0.5);
    }

    @Test
    public void speedingDurationTest(){
        ZonedDateTime now = ZonedDateTime.now();
        Waypoint point1 = new Waypoint( now.minusSeconds(20), new Position( 18.0667,59.334), 6.3889, 8.33);
        Waypoint point2 = new Waypoint( now.minusSeconds(15), new Position( 18.0662,59.3337), 9.4, 8.33);
        Waypoint point3 = new Waypoint( now.minusSeconds(10), new Position( 18.0664,59.3331), 11.1, 8.33);
        Waypoint point4 = new Waypoint( now.minusSeconds(5), new Position( 18.0665,59.3327), 8.32, 8.33);
        Waypoint point5 = new Waypoint( now, new Position( 18.0666,59.3323), 8.33, 8.33);
        List<Waypoint> points = Arrays.asList(point1, point2, point3, point4, point5);

        double total = categorizer.calculateDurationSpeeding( points );
        assertEquals(10, total, 0.01);
    }

    @Test
    public void speedingDurationSamePointsTest(){
        ZonedDateTime now = ZonedDateTime.now();
        Waypoint point1 = new Waypoint( now, new Position( 18.0666,59.3323), 8.33, 8.33);
        Waypoint point2 = new Waypoint( now, new Position( 18.0666,59.3323), 8.33, 8.33);
        List<Waypoint> points = Arrays.asList(point1, point2);

        double total = categorizer.calculateDurationSpeeding( points );
        assertEquals(0, total, 0.01);
    }

    @Test
    public void speedingDistanceTest(){
        ZonedDateTime now = ZonedDateTime.now();
        Waypoint point1 = new Waypoint( now.minusSeconds(20), new Position( 18.0667,59.334), 6.3889, 8.33);
        Waypoint point2 = new Waypoint( now.minusSeconds(15), new Position( 18.0662,59.3337), 9.4, 8.33);
        Waypoint point3 = new Waypoint( now.minusSeconds(10), new Position( 18.0664,59.3331), 11.1, 8.33);
        Waypoint point4 = new Waypoint( now.minusSeconds(5), new Position( 18.0665,59.3327), 8.32, 8.33);
        Waypoint point5 = new Waypoint( now, new Position( 18.0666,59.3323), 8.33, 8.33);
        List<Waypoint> points = Arrays.asList(point1, point2, point3, point4, point5);

        double total = categorizer.calculateDistanceSpeeding( points );
        assertEquals(112, total, 0.5);
    }

    @Test
    public void speedingDistanceSamePointsTest(){
        ZonedDateTime now = ZonedDateTime.now();
        Waypoint point1 = new Waypoint( now, new Position( 18.0666,59.3323), 8.33, 8.33);
        Waypoint point2 = new Waypoint( now, new Position( 18.0666,59.3323), 8.33, 8.33);
        List<Waypoint> points = Arrays.asList(point1, point2);

        double total = categorizer.calculateDistanceSpeeding( points );
        assertEquals(0, total, 0.5);
    }
}
