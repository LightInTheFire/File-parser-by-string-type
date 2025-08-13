package ru.light.parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.light.config.FilterConfiguration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ArgumentsParserTest {
    static FilterConfiguration defaultConfiguration;
    @BeforeEach
    void setUp() {
        defaultConfiguration = FilterConfiguration.builder().build();
    }

    @Test
    void shouldThrowWithoutArgs() {
        String[] emptyArgs = new String[0];

        assertThrows(IllegalArgumentException.class, () -> new ArgumentsParser(emptyArgs));
    }

    @Test
    void shouldCreateDefaultConfig() {
        String[] args = {"text.txt"};
        ArgumentsParser argumentsParser = new ArgumentsParser(args);
        FilterConfiguration filterConfiguration = argumentsParser.getFilterConfiguration();

        assertEquals(defaultConfiguration, filterConfiguration);
    }

    @Test
    void shouldNotUpdatePrefixWithInssuficientArgs() {
        String[] args = {"-p"};
        ArgumentsParser argumentsParser = new ArgumentsParser(args);
        FilterConfiguration filterConfiguration = argumentsParser.getFilterConfiguration();

        assertEquals(defaultConfiguration, filterConfiguration);
    }

    @Test
    void shouldNotUpdatePathToSaveWithInssuficientArgs() {
        String[] args = {"-o"};
        ArgumentsParser argumentsParser = new ArgumentsParser(args);
        FilterConfiguration filterConfiguration = argumentsParser.getFilterConfiguration();

        assertEquals(defaultConfiguration, filterConfiguration);
    }

    @Test
    void shouldUpdatePrefix() {
        String[] args = {"-p", "prefix_"};
        ArgumentsParser argumentsParser = new ArgumentsParser(args);
        FilterConfiguration filterConfiguration = argumentsParser.getFilterConfiguration();

        assertEquals("prefix_", filterConfiguration.getPrefix());
    }

    @Test
    void shouldUpdatePathToSave() {
        String[] args = {"-o", "some/path"};
        ArgumentsParser argumentsParser = new ArgumentsParser(args);
        FilterConfiguration filterConfiguration = argumentsParser.getFilterConfiguration();

        assertEquals("some/path", filterConfiguration.getPathToOutputFiles());
    }

    @Test
    void shouldUpdateStatisticsTypeToFull() {
        String[] args = {"-f"};
        ArgumentsParser argumentsParser = new ArgumentsParser(args);
        FilterConfiguration filterConfiguration = argumentsParser.getFilterConfiguration();

        assertEquals(FilterConfiguration.Statistics.FULL, filterConfiguration.getStatistics());
    }

    @Test
    void shouldUpdateStatisticsTypeToSimple() {
        String[] args = {"-s"};
        ArgumentsParser argumentsParser = new ArgumentsParser(args);
        FilterConfiguration filterConfiguration = argumentsParser.getFilterConfiguration();

        assertEquals(FilterConfiguration.Statistics.SIMPLE, filterConfiguration.getStatistics());
    }
}