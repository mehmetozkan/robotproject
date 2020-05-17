package com.mars.robotproject.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class _outputClass implements Serializable {
    private Location[] visitedCells;
    private String[] samplesCollected;
    private long battery;
    private Position finalPosition;

    @JsonProperty("VisitedCells")
    public Location[] getVisitedCells() { return visitedCells; }
    @JsonProperty("VisitedCells")
    public void setVisitedCells(Location[] value) { this.visitedCells = value; }

    @JsonProperty("SamplesCollected")
    public String[] getSamplesCollected() { return samplesCollected; }
    @JsonProperty("SamplesCollected")
    public void setSamplesCollected(String[] value) { this.samplesCollected = value; }

    @JsonProperty("Battery")
    public long getBattery() { return battery; }
    @JsonProperty("Battery")
    public void setBattery(long value) { this.battery = value; }

    @JsonProperty("FinalPosition")
    public Position getFinalPosition() { return finalPosition;    }
    @JsonProperty("FinalPosition")
    public void setFinalPosition(Position finalPosition) { this.finalPosition = finalPosition; }
}
