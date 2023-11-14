package com.playtech.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

public class Utils {
    public static List<String> readData(String fileName) {
        try {
            return Files.lines(Path.of(fileName))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            System.err.println("Error reading file: " + fileName);
            e.printStackTrace();
            return List.of();
        }
    }

    public static void writeResult(List<String> results, String fileName) {
        try {
            Files.write(Path.of(fileName), results);
        } catch (IOException e) {
            System.err.println("Error writing to file: " + fileName);
            e.printStackTrace();
        }
    }
}
