package com.example.back.beans.utils.parsers;

import jakarta.ejb.Local;

import java.io.IOException;
import java.util.Optional;

@Local
public interface JsonParser<T> {
    Optional<T> parseFromJSON(String json) throws IOException;
    String parseToJSON(T t) throws IOException;
}
