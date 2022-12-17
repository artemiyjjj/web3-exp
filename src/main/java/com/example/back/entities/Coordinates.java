package com.example.back.entities;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class Coordinates implements Serializable {

    private double x;
    private double y;
    private int r;

    public Coordinates() {}

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public int getR() {
        return r;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setR(int r) {
        this.r = r;
    }
}
