package com.dingtalk.h5app.quickstart.domain.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
    public  class TaskTopVoEntity {
        private static final long serialVersionUID = 5346968158739858986L;
        private Date createTime;
        private Date finishTime;
        private String taskResult;
        private String taskStatus;
        private String taskid;
        private String url;
        private String userid;

    }


