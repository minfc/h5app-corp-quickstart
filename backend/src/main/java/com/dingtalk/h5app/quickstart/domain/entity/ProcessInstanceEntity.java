package com.dingtalk.h5app.quickstart.domain.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;
@Getter
@Setter
@ToString
public  class ProcessInstanceEntity {
    private static final long serialVersionUID = 1486123242449315498L;
    private List<String> approverUserids;
    private List<String> attachedProcessInstanceIds;
    private String bizAction;
    private String businessId;
    private List<String> ccUserids;
    private Date createTime;
    private Date finishTime;
    private List<FormComponentValueVoEntity> formComponentValues;
    private String mainProcessInstanceId;
    private List<OperationRecordsVoEntity> operationRecords;
    private String originatorDeptId;
    private String originatorDeptName;
    private String originatorUserid;
    private String result;
    private String status;
    private List<TaskTopVoEntity> tasks;
    private String title;
}