package com.example.back.beans.services;

import com.example.back.beans.dao.ShotDAO;
import com.example.back.beans.utils.computer.ShotHitCalculator;
import com.example.back.beans.utils.parsers.ShotJsonParser;
import com.example.back.entities.Coordinates;
import com.example.back.entities.ShotEntity;
import jakarta.ejb.EJB;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Stateless
@LocalBean
public class ShotService {
    @EJB
    private ShotDAO shotDAO;
    @EJB
    private ShotJsonParser shotJsonParser;

    public ShotService() {}

    public Optional<String> handleShot(Coordinates coordinates) {
        long executionStartNanos = System.nanoTime();

        ShotEntity shotEntity = new ShotEntity();
        String resultJson = "";
        ShotHitCalculator shotHitCalculator = new ShotHitCalculator();

        boolean isHit = shotHitCalculator.calculateHit(coordinates);

        shotEntity.setCoordinates(coordinates);
        shotEntity.setHit(isHit);
        shotEntity.setCurrentTime(new Timestamp(System.currentTimeMillis()));

        shotEntity.setExecutionTime((System.nanoTime() - executionStartNanos)   );

        try {
            shotDAO.create(shotEntity);
        } catch (Exception e) {
            return Optional.empty();
        }

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
