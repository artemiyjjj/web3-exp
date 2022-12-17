package com.example.back.beans.utils.parsers;

import java.io.IOException;
import java.util.Optional;

public interface JsonParser<T> {
    Optional<T> parseFromJSON(String json) throws IOException;
    String parseToJSON(T t) throws IOException;
}
