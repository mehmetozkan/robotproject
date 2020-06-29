package com.mars.robotproject.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseData {
    GetAdvancePaymentOrderInfoResponseV1 getAdvancePaymentOrderInfoResponse_v1;

    @JsonProperty("GetAdvancePaymentOrderInfoResponse_v1")
    public GetAdvancePaymentOrderInfoResponseV1 getGetAdvancePaymentOrderInfoResponse_v1() {
        return getAdvancePaymentOrderInfoResponse_v1;
    }

    public void setGetAdvancePaymentOrderInfoResponse_v1(GetAdvancePaymentOrderInfoResponseV1 getAdvancePaymentOrderInfoResponse_v1) {
        this.getAdvancePaymentOrderInfoResponse_v1 = getAdvancePaymentOrderInfoResponse_v1;
    }
}
