package org.consumir_api.utils;

import org.consumir_api.exceptions.DomainException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CreateDirectoriesUtil {

    public static void creatingDirectories(String fileName) {
        Path path = Paths.get(fileName);
        try {
            if (Files.notExists(path)) {
                Files.createDirectories(path);
            }
        }
        catch (Exception e) {
            throw new DomainException("Não foi possível criar a pasta!");
        }
    }
}
