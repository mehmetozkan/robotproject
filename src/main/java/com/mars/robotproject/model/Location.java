package com.mars.robotproject.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Location {
    private long x;
    private long y;

    public Location() {
    }

    public Location(long x, long y) {
        this.x = y;
        this.y = x;
    }

    @JsonProperty("x")
    public long getX() { return x; }
    @JsonProperty("x")
    public void setX(long value) { this.x = value; }

    @JsonProperty("y")
    public long getY() { return y; }
    @JsonProperty("y")
    public void setY(long value) { this.y = value; }
}
