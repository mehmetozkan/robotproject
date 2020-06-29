package com.mars.robotproject.model.mapquest;

@lombok.Data
public class Result {
    private ProvidedLocation providedLocation;
    private Location[] locations;
}
