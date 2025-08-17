package ru.light;

import lombok.Setter;

import java.io.BufferedWriter;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class OutputWriter {
    @Setter
    private StandardOpenOption writerOption;

    public OutputWriter(boolean appendOutput) {
        this.writerOption = appendOutput ? StandardOpenOption.APPEND : StandardOpenOption.TRUNCATE_EXISTING;
    }


    public void writeToFile(Path pathToFile, List<String> content) {

        Path path = ensurePathExists(pathToFile);

        try (BufferedWriter writer = Files.newBufferedWriter(path,
                StandardCharsets.UTF_8,
                StandardOpenOption.CREATE,
                writerOption)) {

            for (String line : content) {
                writer.write(line);
                writer.newLine();
            }

        } catch (Exception e) {
            System.out.printf("Error during writing to file : %s%n", e.getMessage());
        }

    }

    private Path ensurePathExists(Path path) {
        Path currentPath = Paths.get("");
        for (Path pathPiece : path) {
            currentPath = currentPath.resolve(pathPiece);
            File currentFile = currentPath.toFile();
            if (!currentFile.exists() && !pathPiece.toString().endsWith(".txt")) {
                currentFile.mkdir();
            }
        }
        return currentPath;
    }
}
