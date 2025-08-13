package ru.light.parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FileParserTest {
    File file;

    @BeforeEach
    void setUp() throws IOException {
        file = Files.createTempFile("text", ".txt").toFile();

    }

    @Test
    void shouldParseFile() throws IOException {
        FileParser fileParser = new FileParser();

        String str1 = "Hello World!";
        String str2 = "123,qwe";
        String int1 = "123";
        String int2 = "645653453";
        String float1 = "12345.0";
        String float2 = "1.528535047E-25";
        List<String> inputList = List.of(str1, str2, int1, int2, float1, float2);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (String str : inputList) {
                writer.write(str);
                writer.newLine();
            }
        }

        Map<LineType, List<String>> parsedFile = fileParser.parse(file);
        assertEquals(List.of(str1, str2), parsedFile.get(LineType.STRING));
        assertEquals(List.of(float1, float2), parsedFile.get(LineType.FLOAT));
        assertEquals(List.of(int1, int2), parsedFile.get(LineType.INTEGER));
    }
}