package com.mars.robotproject.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class RelatedSalesOrder {
    private List<ID> id;

    @JsonProperty("id")
    public List<ID> getID() { return id; }
    public void setID(List<ID> value) { this.id = value; }
}
