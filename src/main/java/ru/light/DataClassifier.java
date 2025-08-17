package ru.light;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import ru.light.config.FilterConfiguration;
import ru.light.parser.FileParser;
import ru.light.parser.LineType;
import ru.light.statistics.FloatStatistics;
import ru.light.statistics.IntegerStatistics;
import ru.light.statistics.StringStatistics;
import ru.light.statistics.TypeStatistics;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class DataClassifier {
    private static final String INTEGERS_FILENAME = "integers.txt";
    private static final String FLOATS_FILENAME = "floats.txt";
    private static final String STRINGS_FILENAME = "strings.txt";

    FilterConfiguration filterConfiguration;
    FileParser fileParser;
    OutputWriter outputWriter;
    Map<LineType, TypeStatistics> typeStatistics;

    public DataClassifier(FilterConfiguration filterConfiguration) {
        this.filterConfiguration = filterConfiguration;
        this.fileParser = new FileParser();
        this.outputWriter = new OutputWriter(filterConfiguration.isAppendInput());
        this.typeStatistics = new EnumMap<>(LineType.class);
        initializeStatisticsMap();
    }

    public void processFiles(List<File> files) {
        for (File file : files) {

            Map<LineType, List<String>> parsedFile;
            try {
                parsedFile = fileParser.parse(file);
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
                continue;
            }

            String pathToOutputFiles = filterConfiguration.getPathToOutputFiles();
            String filePrefix = filterConfiguration.getPrefix();

            Map<LineType, Path> pathsMap = getNewPathsMap(pathToOutputFiles, filePrefix);

            parsedFile.forEach((lineType, lines)
                    -> typeStatistics.get(lineType).appendElements(lines));

            for (LineType lineType : parsedFile.keySet()) {
                outputWriter.writeToFile(pathsMap.get(lineType),  parsedFile.get(lineType));
            }

            outputWriter.setWriterOption(StandardOpenOption.APPEND);
        }


        if (filterConfiguration.getStatistics() != FilterConfiguration.Statistics.NONE) {
            printStatistics();
        }

        System.out.println("Files filtered");
    }

    private Map<LineType, Path> getNewPathsMap(String pathToOutputFiles, String filePrefix) {
        Map<LineType, Path> pathsMap = new EnumMap<>(LineType.class);

        pathsMap.put(LineType.INTEGER, Paths.get(pathToOutputFiles, filePrefix.concat(INTEGERS_FILENAME)));
        pathsMap.put(LineType.FLOAT, Paths.get(pathToOutputFiles, filePrefix.concat(FLOATS_FILENAME)));
        pathsMap.put(LineType.STRING, Paths.get(pathToOutputFiles, filePrefix.concat(STRINGS_FILENAME)));
        return pathsMap;
    }

    private void printStatistics() {
        for (var statistics : typeStatistics.values()) {
            String statistic = switch (filterConfiguration.getStatistics()) {
                case FULL -> statistics.getFullStatistics();
                case SIMPLE -> statistics.getSimpleStatistics();
                case NONE -> "";
            };
            System.out.println(statistic);
        }
    }


    private void initializeStatisticsMap() {
        typeStatistics.put(LineType.STRING, new StringStatistics());
        typeStatistics.put(LineType.FLOAT, new FloatStatistics());
        typeStatistics.put(LineType.INTEGER, new IntegerStatistics());
    }

}
