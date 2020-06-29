package com.mars.robotproject.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Response {
    private List<ID> id;
    private List<RelatedSalesOrder> relatedSalesOrder;

    @JsonProperty("id")
    public List<ID> getID() { return id; }
    public void setID(List<ID> value) { this.id = value; }

    @JsonProperty("relatedSalesOrder")
    public List<RelatedSalesOrder> getRelatedSalesOrder() { return relatedSalesOrder; }
    public void setRelatedSalesOrder(List<RelatedSalesOrder> value) { this.relatedSalesOrder = value; }
}
