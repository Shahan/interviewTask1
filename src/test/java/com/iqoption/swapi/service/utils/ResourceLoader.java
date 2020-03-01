package com.iqoption.swapi.service.utils;

import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ResourceLoader {
    public static String readFileToString(String path) {
        try {
            URI uri = Thread.currentThread().getContextClassLoader().getResource(path).toURI();
            return new String(Files.readAllBytes(Paths.get(uri)));
        } catch (Exception e) {
           throw new IllegalStateException("Failed to load resource by path " + path);
        }
    }
}
