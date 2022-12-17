package com.example.back.beans.services;

import com.example.back.beans.utils.ShotHitCalculator;
import com.example.back.beans.utils.parsers.ShotJsonParser;
import com.example.back.entities.Coordinates;
import com.example.back.entities.ShotEntity;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Stateless
public class ShotService {
    private ShotEntity shotEntity = new ShotEntity();
    //    @EJB
//    private ShotDAO shotDAO;
    @EJB
    private ShotHitCalculator shotHitCalculator;

    @EJB
    private ShotJsonParser shotJsonParser;

    public ShotService() {
    }

    public Optional<String> handleShot(Coordinates coordinates) {
        return Optional.of("123");
    }

    //        String resultJson = "";
//        long execution_start = System.currentTimeMillis();
//        boolean isHit = shotHitCalculator.calculateHit(coordinates);
//
//        shotEntity.setCoordinates(coordinates);
//        shotEntity.setHit(isHit);
//        shotEntity.setCurrentTime(new Date(System.currentTimeMillis()));
//        shotEntity.setExecutionTime(new Date(System.currentTimeMillis() - execution_start));
//
//        try {
//            shotDAO.create(shotEntity);
//        } catch (Exception e) {
//            return Optional.empty();
//        }
//        int id = shotEntity.getId();
//
//        ShotEntity receivedShot = shotEntity;
////        ShotEntity receivedShot;
////        try {
////            Optional<ShotEntity> receivedOptional = shotDAO.read(id);
////            if (receivedOptional.isPresent()) {
////                receivedShot = receivedOptional.get();
////            }
////            else throw new Exception();
////        } catch (Exception e) {
////            return Optional.empty();
////        }
//
//        try {
//            resultJson = shotJsonParser.parseToJSON(receivedShot);
//        } catch (IOException e) {
//            return Optional.empty();
//        }
//        return Optional.of(resultJson);
//    }
//
    public List<String> getAllShots() {
        List<ShotEntity> listShots;
        List<String> listJsonShots = new ArrayList<>();
        return listJsonShots;
    }
//
//        try {
//            listShots = shotDAO.readAll();
//            for (ShotEntity shot : listShots) {
//                listJsonShots.add(shotJsonParser.parseToJSON(shot));
//            }
//        } catch (IOException e) {
//
//        }
//        return listJsonShots;


}
