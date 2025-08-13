package ru.light.parser;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileParser {

    public Map<LineType, List<String>> parse(File file) {

        Map<LineType, List<String>> parsedFile = initializeMap();

        List<String> lines;
        try {
            lines = Files.readAllLines(file.toPath());
        } catch (IOException e) {
            throw new RuntimeException("Error reading file %s".formatted(file.getPath()));
        }

        for (String line : lines) {
            if (isStringInteger(line)) {
                parsedFile.get(LineType.INTEGER).add(line);
            } else if (isStringFloat(line)) {
                parsedFile.get(LineType.FLOAT).add(line);
            } else {
                parsedFile.get(LineType.STRING).add(line);
            }
        }

        return parsedFile;
    }

    private Map<LineType, List<String>> initializeMap() {
        Map<LineType, List<String>> map = new HashMap<>();
        map.put(LineType.STRING, new ArrayList<>());
        map.put(LineType.INTEGER, new ArrayList<>());
        map.put(LineType.FLOAT, new ArrayList<>());
        return map;
    }

    private boolean isStringInteger(String s) {
        try {
            Long.parseLong(s);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    private boolean isStringFloat(String s) {
        try {
            Double.parseDouble(s);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
}
