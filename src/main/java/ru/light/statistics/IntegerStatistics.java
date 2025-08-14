package ru.light.statistics;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

public class IntegerStatistics extends TypeStatistics {
    private final NumberStatisticsCalculator calculator;

    public IntegerStatistics() {
        this.calculator = new NumberStatisticsCalculator();
    }

    @Override
    public String getSimpleStatistics() {
        return "Amount of integers processed: %d".formatted(elements.size());
    }

    @Override
    public String getFullStatistics() {
        if (elements.isEmpty()) {
            return "No integers were processed";
        }

        List<BigDecimal> convertedElements = elements.stream()
                .map(BigDecimal::new)
                .toList();

        BigInteger min = calculator.calculateMin(convertedElements).toBigInteger();
        BigInteger max = calculator.calculateMax(convertedElements).toBigInteger();
        BigInteger sum = calculator.calculateSum(convertedElements).toBigInteger();
        BigInteger mean = calculator.calculateMean(convertedElements).toBigInteger();

        return """
                           Integers statistics
                    *** min: %d
                    *** max: %d
                    *** sum: %d
                    *** mean: %d
                    *** number of elements: %d
                """.formatted(min, max, sum, mean, convertedElements.size());
    }
}
