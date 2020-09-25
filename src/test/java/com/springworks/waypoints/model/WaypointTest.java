package com.springworks.waypoints.model;

import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class WaypointTest {

    @Test
    public void createWaypoint(){
        Position position = new Position( 18.0667,59.334);
        Waypoint waypoint = new Waypoint( ZonedDateTime.now(), position, 6.3889, 8.33);
        assertEquals(6.3889, waypoint.getSpeed());
        assertEquals(8.33, waypoint.getSpeedLimit());
        assertEquals(59.334, waypoint.getLatitude());
        assertEquals(18.0667, waypoint.getLongitude());
    }

    @Test
    public void createWaypointInvalidDate(){
        assertThrows(IllegalArgumentException.class, () ->
            new Waypoint( ZonedDateTime.now().plusDays(1), new Position( 180,59.334), 6.3889, 8.33)
        );
    }

    @Test
    public void createWaypointInvalidLongitutePosition(){
        assertThrows(IllegalArgumentException.class, () ->
                new Waypoint( ZonedDateTime.now(), new Position( 181,59.334), 6.3889, 8.33)
        );
    }

    @Test
    public void createWaypointInvalidLatitudePosition(){
        assertThrows(IllegalArgumentException.class, () ->
                new Waypoint( ZonedDateTime.now(), new Position( 180,91), 6.3889, 8.33)
        );
    }

    @Test
    public void createWaypointInvalidSpeed(){
        assertThrows(IllegalArgumentException.class, () ->
                new Waypoint( ZonedDateTime.now(), new Position( 180,59.334), -1.0, 8.33)
        );
    }

    @Test
    public void createWaypointInvalidSpeedLimit(){
        assertThrows(IllegalArgumentException.class, () ->
                new Waypoint( ZonedDateTime.now(), new Position( 180,59.334), 8.33, -1.0)
        );
    }
}
