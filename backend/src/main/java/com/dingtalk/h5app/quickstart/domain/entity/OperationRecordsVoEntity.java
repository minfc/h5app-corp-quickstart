package com.dingtalk.h5app.quickstart.domain.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
public  class OperationRecordsVoEntity {
    private static final long serialVersionUID = 3812978842318432616L;
    private Date date;
    private String operationResult;
    private String operationType;
    private String remark;
    private String userid;

}