package com.example.back.beans.utils;

import com.example.back.beans.utils.computer.Computer;
import com.example.back.entities.Coordinates;
import jakarta.ejb.Stateless;

@Stateless
public class ShotHitCalculator implements Computer<Coordinates> {

    public ShotHitCalculator() {}

    public boolean calculateHit(Coordinates coordinates) {
        return isHitting(coordinates);
    }

    private boolean isHitting(Coordinates coordinates) {
        Point point = new Point(coordinates.getX(), coordinates.getY());
        int r = coordinates.getR();

        // north-condition
        if ((point.getX() <= r / 2 && point.getX() >= 0) && (point.getY() <= r && point.getY() >= 0)) {
            return true;
        }
        // south-east-condition
        else if (isHittingTriangle(point, r, new Point(0, 0), new Point(0, r), new Point(r, 0))) {
            return true;
        }
        // south-west-condition                                 // length
        else if ((point.getX() <= 0 && point.getY() < 0) && (Math.sqrt(Math.pow(point.getX(), 2) + Math.pow(point.getY(), 2)) <= r)) {
            return true;
        }
        // missing
        else {
            return false;
        }
    }

    private boolean isHittingTriangle(Point point, double r, Point apex1, Point apex2, Point apex3) {
        double condition1 = (apex1.getX() - point.getX()) * (apex2.getY() - apex1.getY()) - (apex2.getX() - apex1.getX()) * (apex1.getY() - point.getY());
        double condition2 = (apex2.getX() - point.getX()) * (apex3.getY() - apex2.getY()) - (apex3.getX() - apex2.getX()) * (apex2.getY() - point.getY());
        double condition3 = (apex3.getX() - point.getX()) * (apex1.getY() - apex3.getY()) - (apex1.getX() - apex3.getX()) * (apex3.getY() - point.getY());
        return (condition1 >= 0 && condition2 >= 0 && condition3 >= 0) || (condition1 <= 0 && condition2 <= 0 && condition3 <= 0);
    }
}

class Point {
    private final double x;
    private final double y;

    Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    double getX() {
        return x;
    }

    double getY() {
        return y;
    }
}
