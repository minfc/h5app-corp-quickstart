package com.dingtalk.h5app.quickstart.domain.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public  class FormComponentValueVoEntity {
    private static final long serialVersionUID = 2432289924163259469L;
    private String componentType;
    private String extValue;
    private String id;
    private String name;
    private String value;
}