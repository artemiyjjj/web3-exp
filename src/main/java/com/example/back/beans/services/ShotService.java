package com.example.back.beans.services;

import com.example.back.beans.dao.ShotDAO;
import com.example.back.beans.utils.ShotHitCalculator;
import com.example.back.beans.utils.parsers.ShotJsonParser;
import com.example.back.entities.Coordinates;
import com.example.back.entities.ShotEntity;
import jakarta.ejb.Stateless;

import java.io.IOException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Stateless
public class ShotService {

    private final ShotDAO shotDAO = new ShotDAO();

    private final ShotJsonParser shotJsonParser = new ShotJsonParser();

    public ShotService() {}

    public Optional<String> handleShot(Coordinates coordinates) {
        System.out.println("shot handling started...");
        long execution_start = System.currentTimeMillis();
        ShotEntity shotEntity = new ShotEntity();
        String resultJson = "";
        ShotHitCalculator shotHitCalculator = new ShotHitCalculator();

        boolean isHit = shotHitCalculator.calculateHit(coordinates);

        System.out.println("hit calculated...");
        shotEntity.setCoordinates(coordinates);
        shotEntity.setHit(isHit);
        shotEntity.setCurrentTime(new Timestamp(System.currentTimeMillis()));
        shotEntity.setExecutionTime(new Time(System.currentTimeMillis() - execution_start));

        System.out.println("starting transaction...");
        try {
            shotDAO.create(shotEntity);
        } catch (Exception e) {
            return Optional.empty();
        }
        System.out.println("transaction ended...\nstarting jackson");

        try {
            resultJson = shotJsonParser.parseToJSON(shotEntity);
        } catch (IOException e) {
            return Optional.empty();
        }
        return Optional.of(resultJson);
    }

    public List<String> getAllShots() {
        List<ShotEntity> listShots;
        List<String> listJsonShots = new ArrayList<>();

        try {
            listShots = shotDAO.readAll();
            for (ShotEntity shot : listShots) {
                listJsonShots.add(shotJsonParser.parseToJSON(shot));
            }
        } catch (IOException e) {
            System.out.println("Sheeet");
        }
        return listJsonShots;
    }

}
