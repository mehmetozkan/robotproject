package com.mars.robotproject.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ID {
    private String schemeName;
    private String value;

    public ID() {
    }

    public ID(String schemeName, String value) {
        this.schemeName = schemeName;
        this.value = value;
    }

    @JsonProperty("schemeName")
    public String getSchemeName() { return schemeName; }
    public void setSchemeName(String value) { this.schemeName = value; }

    @JsonProperty("value")
    public String getValue() { return value; }
    public void setValue(String value) { this.value = value; }

    @Override
    public String toString() {
        return "ID{" +
                "schemeName='" + schemeName + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
