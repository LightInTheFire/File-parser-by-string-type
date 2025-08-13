package ru.light;

import ru.light.config.FilterConfiguration;
import ru.light.parser.ArgumentsParser;
import ru.light.parser.FileParser;

public class Main {
    public static void main(String[] args) {

        ArgumentsParser argumentsParser;
        try {
            argumentsParser = new ArgumentsParser(args);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }

        FilterConfiguration filterConfiguration = argumentsParser.getFilterConfiguration();

        DataClassifier dataClassifier = new DataClassifier(filterConfiguration,
                new FileParser(),
                new OutputWriter());
        dataClassifier.processFiles(argumentsParser.getInputFiles());
    }
}
