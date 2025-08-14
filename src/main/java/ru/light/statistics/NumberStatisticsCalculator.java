package ru.light.statistics;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class NumberStatisticsCalculator {


    public BigDecimal calculateMin(List<BigDecimal> numbers) {
        return numbers.stream()
                .min(BigDecimal::compareTo)
                .get();
    }

    public BigDecimal calculateMax(List<BigDecimal> numbers) {
        return numbers
                .stream()
                .max(BigDecimal::compareTo)
                .get();
    }

    public BigDecimal calculateSum(List<BigDecimal> numbers) {
        return numbers.stream()
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal calculateMean(List<BigDecimal> numbers) {
        return numbers.stream()
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .divide(BigDecimal.valueOf(numbers.size()), RoundingMode.HALF_UP);
    }
}
