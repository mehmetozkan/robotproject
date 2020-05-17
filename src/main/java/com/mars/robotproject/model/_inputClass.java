package com.mars.robotproject.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class _inputClass implements Serializable {
    private String[][] terrain;
    private long battery;
    private String[] commands;
    private Position initialPosition;

    @JsonProperty("terrain")
    public String[][] getTerrain() { return terrain; }
    @JsonProperty("terrain")
    public void setTerrain(String[][] value) { this.terrain = value; }

    @JsonProperty("battery")
    public long getBattery() { return battery; }
    @JsonProperty("battery")
    public void setBattery(long value) { this.battery = value; }

    @JsonProperty("commands")
    public String[] getCommands() { return commands; }
    @JsonProperty("commands")
    public void setCommands(String[] value) { this.commands = value; }

    @JsonProperty("initialPosition")
    public Position getInitialPosition() { return initialPosition; }
    @JsonProperty("initialPosition")
    public void setInitialPosition(Position value) { this.initialPosition = value; }

}
