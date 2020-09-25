package com.springworks.waypoints.parser;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springworks.waypoints.model.Waypoint;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.JsonNode;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

public class JsonParserTest {

    private JsonParser parser = new JsonParser();

    @Test
    public void shouldParse(){
        String filename = "waypoints.json";
        assertTrue(parser.shouldParse( filename ));
    }

    @Test
    public void shouldNotParse(){
        String filename = "waypoints.xml";
        assertFalse(parser.shouldParse( filename ));
    }

    @Test
    public void parserFile() throws IOException {
        String path = "src/test/resources/waypoints.json";
        File file = new File(path);
        String filename = file.getAbsolutePath();
        List<JsonNode> node = parser.parse(filename);
        List<Waypoint> points = new ObjectMapper().convertValue( node, new TypeReference<List<Waypoint>>(){} );
        assertEquals(6.3889, points.get(0).getSpeed(), 0.01);
        assertEquals(9.4, points.get(1).getSpeed(), 0.01);
    }
}
