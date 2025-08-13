package ru.light;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import ru.light.config.FilterConfiguration;
import ru.light.parser.FileParser;

import java.io.File;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class DataClassifier {
    FilterConfiguration filterConfiguration;
    FileParser fileParser;
    OutputWriter outputWriter;

    public void processFiles(List<File> files) {

    }
}
