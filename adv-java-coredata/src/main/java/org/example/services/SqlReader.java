package org.example.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class SqlReader {
    private SqlReader() {
    }
    public static String readAll(String readPath) throws IOException {
        return Files.readString(Path.of(readPath));
    }
}
