package ru.light.config;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Builder
@Getter
@EqualsAndHashCode
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FilterConfiguration {
    @Builder.Default
    boolean appendInput = false;
    @Builder.Default
    String prefix = "";
    @Builder.Default
    Statistics statistics = Statistics.NONE;
    @Builder.Default
    String pathToOutputFiles = "";

    public enum Statistics {
        FULL, SIMPLE, NONE
    }
}
