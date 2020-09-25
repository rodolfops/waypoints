package com.springworks.waypoints.parser;

import com.fasterxml.jackson.databind.JsonNode;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class GenericParser {
    private List<Parser> parsers;

    public GenericParser() {
        this.parsers = Arrays.asList(
                new JsonParser()
        );
    }

    public List<JsonNode> parse(String file) throws IOException {
        for (Parser parser : parsers) {
            if (parser.shouldParse(file)) {
                return parser.parse(file);
            }
        }
        return null;
    }

}
