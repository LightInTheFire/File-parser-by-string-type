package ru.light.statistics;

import org.junit.jupiter.api.Test;

import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class StringStatisticsTest {

    @Test
    void shouldReturnCorrectStatistics() {
        List<String> list = List.of("1", "22","55555", "333", "4444");
        StringStatistics stringStatistics = new StringStatistics();
        stringStatistics.appendElements(list);

        String expected = """
                        Strings statistics
                    *** min length: %d
                    *** max length: %d
                    *** number of elements: %d
                """.formatted(1, 5, list.size());


        assertEquals(expected, stringStatistics.getFullStatistics());
    }
}