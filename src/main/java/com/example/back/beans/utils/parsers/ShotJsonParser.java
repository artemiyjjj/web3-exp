package com.example.back.beans.utils.parsers;

import com.example.back.entities.ShotEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.ejb.Stateless;

import java.io.IOException;
import java.util.Optional;

@Stateless
public class ShotJsonParser implements JsonParser<ShotEntity> {

    @Override
    public String parseToJSON(ShotEntity responseBody) throws IOException {
        return new ObjectMapper().writeValueAsString(responseBody);
    }

    @Override
    public Optional<ShotEntity> parseFromJSON(String json) throws IOException {
        return Optional.of(new ObjectMapper().readValue(json, ShotEntity.class));
    }

    public ShotJsonParser() {}
}
