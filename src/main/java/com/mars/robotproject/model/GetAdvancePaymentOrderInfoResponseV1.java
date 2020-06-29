package com.mars.robotproject.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GetAdvancePaymentOrderInfoResponseV1 {
    private Header header;
    private Body body;

    @JsonProperty("Header")
    public Header getHeader() { return header; }
    public void setHeader(Header value) { this.header = value; }

    @JsonProperty("Body")
    public Body getBody() { return body; }
    public void setBody(Body value) { this.body = value; }

}
