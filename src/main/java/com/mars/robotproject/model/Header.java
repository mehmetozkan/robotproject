package com.mars.robotproject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;

public class Header {
    private LocalDateTime timestamp;
    private ErrorCode errorCode;
    private List<String> description;
    private String reasonCode;

    public Header() {
    }

    @JsonIgnore
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    @JsonProperty("ErrorCode")
    public ErrorCode getErrorCode() { return errorCode; }
    public void setErrorCode(ErrorCode value) { this.errorCode = value; }

    @JsonProperty("Description")
    public List<String> getDescription() { return description; }
    public void setDescription(List<String> value) { this.description = value; }

    @JsonProperty("ReasonCode")
    public String getReasonCode() { return reasonCode; }
    public void setReasonCode(String value) { this.reasonCode = value; }
}
