package com.example.back.beans.utils.parsers;

import com.example.back.entities.Coordinates;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.servlet.ServletInputStream;

import java.io.IOException;
import java.util.Optional;

@Stateless
@LocalBean
public class CoordinatesParser {
    public Optional<Coordinates> parseFromServletInputStream(ServletInputStream input) {
        try {
            return Optional.of(new ObjectMapper().readValue(input, Coordinates.class));
        } catch (IOException e) {
            return Optional.empty();
        }
    }

    public CoordinatesParser() {}
}
