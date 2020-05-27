package com.dingtalk.h5app.quickstart.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class RowValue implements Serializable {
    private String componentType;
    private String label;
    private String value;
    private String key;

}
