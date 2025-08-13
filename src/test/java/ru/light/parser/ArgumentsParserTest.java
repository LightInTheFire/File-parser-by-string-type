package ru.light.parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.light.config.FilterConfiguration;

import java.io.IOException;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ArgumentsParserTest {
    static FilterConfiguration defaultConfiguration;
    static String pathToTempFile;

    @BeforeEach
    void setUp() throws IOException {
        defaultConfiguration = FilterConfiguration.builder().build();
        pathToTempFile = Files.createTempFile("text", ".txt").toFile().getPath();
    }

    @Test
    void shouldThrowWithoutArgs() {
        String[] emptyArgs = new String[0];

        assertThrows(IllegalArgumentException.class, () -> new ArgumentsParser(emptyArgs));
    }

    @Test
    void shouldCreateDefaultConfig() {
        String[] args = {pathToTempFile};
        ArgumentsParser argumentsParser = new ArgumentsParser(args);
        FilterConfiguration filterConfiguration = argumentsParser.getFilterConfiguration();

        assertEquals(defaultConfiguration, filterConfiguration);
    }

    @Test
    void shouldNotUpdatePrefixWithInssuficientArgs() {
        String[] args = {"-p", pathToTempFile};
        ArgumentsParser argumentsParser = new ArgumentsParser(args);
        FilterConfiguration filterConfiguration = argumentsParser.getFilterConfiguration();

        assertEquals(defaultConfiguration, filterConfiguration);
    }

    @Test
    void shouldNotUpdatePathToSaveWithInssuficientArgs() {
        String[] args = {"-o", pathToTempFile};
        ArgumentsParser argumentsParser = new ArgumentsParser(args);
        FilterConfiguration filterConfiguration = argumentsParser.getFilterConfiguration();

        assertEquals(defaultConfiguration, filterConfiguration);
    }

    @Test
    void shouldUpdatePrefix() {
        String[] args = {"-p", "prefix_", pathToTempFile};
        ArgumentsParser argumentsParser = new ArgumentsParser(args);
        FilterConfiguration filterConfiguration = argumentsParser.getFilterConfiguration();

        assertEquals("prefix_", filterConfiguration.getPrefix());
    }

    @Test
    void shouldUpdatePathToSave() {
        String[] args = {"-o", "some/path", pathToTempFile};
        ArgumentsParser argumentsParser = new ArgumentsParser(args);
        FilterConfiguration filterConfiguration = argumentsParser.getFilterConfiguration();

        assertEquals("some/path", filterConfiguration.getPathToOutputFiles());
    }

    @Test
    void shouldUpdateStatisticsTypeToFull() {
        String[] args = {"-f", pathToTempFile};
        ArgumentsParser argumentsParser = new ArgumentsParser(args);
        FilterConfiguration filterConfiguration = argumentsParser.getFilterConfiguration();

        assertEquals(FilterConfiguration.Statistics.FULL, filterConfiguration.getStatistics());
    }

    @Test
    void shouldUpdateStatisticsTypeToSimple() {
        String[] args = {"-s", pathToTempFile};
        ArgumentsParser argumentsParser = new ArgumentsParser(args);
        FilterConfiguration filterConfiguration = argumentsParser.getFilterConfiguration();

        assertEquals(FilterConfiguration.Statistics.SIMPLE, filterConfiguration.getStatistics());
    }

    @Test
    void shouldThrowWhenNoFilesToProcessProvided() {
        String[] args = {"-f", "-a"};
        ArgumentsParser argumentsParser = new ArgumentsParser(args);

        assertThrows(IllegalArgumentException.class, argumentsParser::getFilterConfiguration);
    }
}