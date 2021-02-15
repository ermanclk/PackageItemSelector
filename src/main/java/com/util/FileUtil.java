package com.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileUtil {

    private FileUtil(){
            throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }

    public static List<String> readLines(String path) {

        try (Stream<String> stream = Files.lines(Paths.get(path))) {
            return stream.filter(line -> !line.isBlank()).collect(Collectors.toList());
        } catch (IOException e) {
            throw new IllegalArgumentException("Unable to find/read specified file, please check file path: " + path,e);
        }
    }

}
