package com.mars.robotproject.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Body {
    private Response response;

    @JsonProperty("Response")
    public Response getResponse() { return response; }
    public void setResponse(Response value) { this.response = value; }
}
