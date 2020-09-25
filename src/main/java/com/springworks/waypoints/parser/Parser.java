package com.springworks.waypoints.parser;

import com.fasterxml.jackson.databind.JsonNode;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface Parser {

    boolean shouldParse(String file);

    default Optional<String> getExtensionByStringHandling(String filename) {
        return Optional.ofNullable(filename)
                .filter(f -> f.contains("."))
                .map(f -> f.substring(filename.lastIndexOf(".") + 1));
    }

    List<JsonNode> parse(String filename) throws IOException;
}
