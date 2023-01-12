package com.example.back.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Embedded;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;

import java.sql.Time;
import java.sql.Timestamp;

@Entity
@Table(name = "Shots", schema = "public")
public class ShotEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride( name = "x", column = @Column(name = "x", nullable = false)),
            @AttributeOverride( name = "y", column = @Column(name = "y", nullable = false)),
            @AttributeOverride( name = "r", column = @Column(name = "r", nullable = false))
    })
    private Coordinates coordinates;
    @Column(name = "is_hit", nullable = false)
    private boolean isHit;
    @Column(name = "execution_time", nullable = false)
    private Time executionTime;
    @Column(name = "current_time", nullable = false)
    private Timestamp currentTime;

    public ShotEntity() {}

    public Time getExecutionTime() {
        return executionTime;
    }

    public Timestamp getCurrentTime() {
        return currentTime;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public int getId() {
        return id;
    }

    public boolean isHit() { return isHit; }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public void setExecutionTime(Time executionTime) {
        this.executionTime = executionTime;
    }

    public void setCurrentTime(Timestamp currentTime) {
        this.currentTime = currentTime;
    }

    public void setHit(boolean hit) { isHit = hit; }
}
