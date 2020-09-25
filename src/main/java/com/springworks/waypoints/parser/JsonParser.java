package com.springworks.waypoints.parser;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class JsonParser implements Parser {
    private static final String JSON = "json";

    @Override
    public boolean shouldParse(String filename) {
        return getExtensionByStringHandling(filename).filter(JSON::equals)
                .isPresent();
    }

    @Override
    public List<JsonNode> parse(String filename) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(new File(filename), new TypeReference<List<JsonNode>>(){});
    }
}
