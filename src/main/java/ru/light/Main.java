package ru.light;

import ru.light.config.FilterConfiguration;
import ru.light.parser.ArgumentsParser;
import ru.light.parser.FileParser;

public class Main {
    public static void main(String[] args) {

        ArgumentsParser argumentsParser;
        FilterConfiguration filterConfiguration;
        try {
            argumentsParser = new ArgumentsParser(args);
            filterConfiguration = argumentsParser.getFilterConfiguration();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }


        DataClassifier dataClassifier = new DataClassifier(filterConfiguration);
        dataClassifier.processFiles(argumentsParser.getInputFiles());
    }
}
