package com.example.back.entities;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "Shots")
public class ShotEntity {

    @Id
    @GeneratedValue
    private int id;
    @Column(name = "execution_time")
    private Date executionTime;
    @Column(name = "current_time")
    private Date currentTime;
    @Column(name = "is_hit")
    private boolean isHit;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride( name = "x", column = @Column(name = "x")),
            @AttributeOverride( name = "y", column = @Column(name = "y")),
            @AttributeOverride( name = "r", column = @Column(name = "r"))
    })
    private Coordinates coordinates;

    public ShotEntity() {}

    public Date getExecutionTime() {
        return executionTime;
    }

    public Date getCurrentTime() {
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

    public void setExecutionTime(Date executionTime) {
        this.executionTime = executionTime;
    }

    public void setCurrentTime(Date currentTime) {
        this.currentTime = currentTime;
    }

    public void setHit(boolean hit) { isHit = hit; }
}
