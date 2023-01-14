package com.example.back.beans.services;

import com.example.back.beans.utils.parsers.CoordinatesParser;
import com.example.back.entities.Coordinates;

import jakarta.ejb.EJB;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.servlet.ServletInputStream;

import java.util.NoSuchElementException;
import java.util.Optional;

@Stateless
@LocalBean
public class CoordinatesService {

    @EJB
    private CoordinatesParser coordinatesParser;

    public CoordinatesService() {}

    public Coordinates handleCoordinates(ServletInputStream inputStream) throws NoSuchElementException {
        Optional<Coordinates> maybeCoordinates = coordinatesParser.parseFromServletInputStream(inputStream);
        if (maybeCoordinates.isPresent()) {
            return maybeCoordinates.get();
        }
        else {
            throw new NoSuchElementException();
        }
    }
}
