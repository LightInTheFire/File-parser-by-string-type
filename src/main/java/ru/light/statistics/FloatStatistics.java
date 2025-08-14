package ru.light.statistics;

import java.math.BigDecimal;
import java.util.List;


public class FloatStatistics extends TypeStatistics {
    private final NumberStatisticsCalculator calculator;

    public FloatStatistics() {
        this.calculator = new NumberStatisticsCalculator();
    }

    @Override
    public String getSimpleStatistics() {
        return "Amount of floats processed: %d".formatted(elements.size());
    }

    @Override
    public String getFullStatistics() {
        if (elements.isEmpty()) {
            return "No floats were processed";
        }

        List<BigDecimal> convertedElements = elements.stream()
                .map(BigDecimal::new)
                .toList();

        BigDecimal min = calculator.calculateMin(convertedElements);
        BigDecimal max = calculator.calculateMax(convertedElements);
        BigDecimal sum = calculator.calculateSum(convertedElements);
        BigDecimal mean = calculator.calculateMean(convertedElements);

        return """
                        Floats statistics
                    *** min: %f
                    *** max: %f
                    *** sum: %f
                    *** mean: %f
                    *** number of elements: %d
                """.formatted(min, max, sum, mean, convertedElements.size());
    }
}
