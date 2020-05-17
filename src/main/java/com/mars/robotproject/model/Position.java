package com.mars.robotproject.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Position {
    private Location location;
    private String facing;

    public Position(){

    }

    public Position(Location location, String facing) {
        this.location = location;
        this.facing = facing;
    }

    @JsonProperty("location")
    public Location getLocation() { return location; }
    @JsonProperty("location")
    public void setLocation(Location value) { this.location = value; }

    @JsonProperty("facing")
    public String getFacing() { return facing; }
    @JsonProperty("facing")
    public void setFacing(String value) { this.facing = value; }
}
