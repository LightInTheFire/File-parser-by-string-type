package ru.light.statistics;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FloatStatisticsTest {

    @Test
    void shouldReturnCorrectStatistics() {
        List<String> list = List.of("2.5", "4.5");
        FloatStatistics doubleStatistics = new FloatStatistics();
        doubleStatistics.appendElements(list);

        String expected = """
                    Floats statistics
                *** min: %f
                *** max: %f
                *** sum: %f
                *** mean: %f
                *** number of elements: %d
            """.formatted(2.5d, 4.5d, 7d, 3.5d, 2);

        assertEquals(expected, doubleStatistics.getFullStatistics());
    }
}