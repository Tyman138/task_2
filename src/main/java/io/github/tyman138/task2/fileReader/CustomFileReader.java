package io.github.tyman138.task2.fileReader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CustomFileReader {
    public List<String> readFileFromLocal(String filePath) {
        List<String> strings = new ArrayList<>();
        try (Stream<String> lines = Files.lines(Paths.get(filePath))) {
            strings = lines.collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return strings;
    }
}
