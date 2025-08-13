package ru.light.parser;

import lombok.Getter;
import ru.light.config.FilterConfiguration;

import java.io.File;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

import static ru.light.config.FilterConfiguration.Statistics;

public class ArgumentsParser {
    private final String[] args;
    @Getter
    private final List<File> inputFiles;

    public ArgumentsParser(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("No arguments provided");
        }

        this.args = args;
        this.inputFiles = new LinkedList<>();
    }

    public FilterConfiguration getFilterConfiguration() {
        FilterConfiguration.FilterConfigurationBuilder filterConfigBuilder = FilterConfiguration.builder();

        for (int i = 0, argsLength = args.length; i < argsLength; i++) {

            String arg = args[i];

            if (arg.startsWith("-")) {
                Option optionByValue = Option.getOptionByValue(arg.substring(1));
                switch (optionByValue) {
                    case PATH_TO_OUTPUT -> {
                        if (i + 1 < argsLength) {
                            filterConfigBuilder.pathToOutputFiles(args[i + 1]);
                            i++;
                        } else {
                            System.out.println("No path to output files provided, will be used pwd");
                        }
                    }
                    case OUTPUT_FILE_PREFIX -> {
                        if (i + 1 < argsLength) {
                            filterConfigBuilder.prefix(args[i + 1]);
                            i++;
                        } else {
                            System.out.println("No output file prefix provided, none will be used");
                        }
                    }
                    case APPEND_OUTPUT -> filterConfigBuilder.appendInput(true);
                    case FULL_STATISTICS -> filterConfigBuilder.statistics(Statistics.FULL);
                    case SIMPLE_STATISTICS -> filterConfigBuilder.statistics(Statistics.SIMPLE);
                    case UNKNOWN -> System.out.printf("Unknown argument provided: %s%n", arg);
                }
            } else if (arg.endsWith(".txt")) {
                File inputFile;
                try {
                    inputFile = Paths.get(arg).toFile();
                } catch (RuntimeException e) {
                    System.out.printf("No such file or directory: %s%n", arg);
                    continue;
                }
                inputFiles.add(inputFile);
            } else {
                System.out.printf("Unknown argument provided: %s%n", arg);
            }
        }

        return filterConfigBuilder.build();
    }
}
