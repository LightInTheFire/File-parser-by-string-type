package ru.light.statistics;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class IntegerStatisticsTest {

    @Test
    void shouldReturnCorrectStatistics() {
        List<String> list = List.of("2", "4");
        IntegerStatistics integerStatistics = new IntegerStatistics();
        integerStatistics.appendElements(list);

        String expected = """
                           Integers statistics
                    *** min: %d
                    *** max: %d
                    *** sum: %d
                    *** mean: %d
                    *** number of elements: %d
                """.formatted(2, 4, 6, 3, 2);
        assertEquals(expected, integerStatistics.getFullStatistics());
    }
}