package ru.light.parser;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum Option {
    PATH_TO_OUTPUT("o"),
    OUTPUT_FILE_PREFIX("p"),
    APPEND_OUTPUT("a"),
    FULL_STATISTICS("f"),
    SIMPLE_STATISTICS("s"),
    UNKNOWN("");

    private final String value;

    Option(String value) {
        this.value = value;
    }

    public static Option getOptionByValue(String value) {
        return Arrays.stream(Option.values())
                .filter(option -> option.getValue().equals(value))
                .findFirst()
                .orElse(UNKNOWN);
    }
}
