package com.mars.robotproject.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ErrorCode {
    private String dialect;

    @JsonProperty("dialect")
    public String getDialect() { return dialect; }
    public void setDialect(String value) { this.dialect = value; }
}
