package ru.light.statistics;

import java.util.NavigableSet;
import java.util.TreeSet;

public class StringStatistics extends TypeStatistics {
    NavigableSet<Integer> stringSizes;

    public StringStatistics() {
    }

    @Override
    public String getSimpleStatistics() {
        return "Amount of strings processed: %d".formatted(elements.size());
    }

    @Override
    public String getFullStatistics() {
        if (elements.isEmpty()) {
            return "No strings were processed";
        }

        calculateStatistics();
        int minStringSize = stringSizes.first();
        int maxStringSize = stringSizes.last();

        return """
                        Strings statistics
                    *** min length: %d
                    *** max length: %d
                    *** number of elements: %d
                """.formatted(minStringSize, maxStringSize, elements.size());
    }

    private void calculateStatistics() {
        stringSizes = new TreeSet<>();

        for (String element : elements) {
            stringSizes.add(element.length());
        }

    }

}
