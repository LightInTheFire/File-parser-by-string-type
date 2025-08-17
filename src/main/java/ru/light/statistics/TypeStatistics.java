package ru.light.statistics;

import java.util.ArrayList;
import java.util.List;

public abstract class TypeStatistics {
    protected List<String> elements;

    protected TypeStatistics() {
        this.elements = new ArrayList<>();
    }

    public abstract String getSimpleStatistics();

    public abstract String getFullStatistics();

    public void appendElements(List<String> newElements) {
        this.elements.addAll(newElements);
    }
}
