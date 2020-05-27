package com.dingtalk.h5app.quickstart.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.taobao.api.internal.mapping.ApiField;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Getter
@Setter
//没有排序字段
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
@Entity
@Table (name = "instanceBookMarkEntityVO")
public class ProcessModuleVoEntity implements Serializable{
    //private static final long serialVersionUID = 4391466827572748695L;
    //private List<ProcessInstanceEntity> processList;
    @Id
    @Column(name = "icon_url")
    @ApiField("icon_url")
    private String iconUrl;
    @Column(name = "name")
    @ApiField("name")
    private String name;
    @Column(name = "processCode")
    @ApiField("process_code")
    private String processCode;
    @Column(name = "url")
    @ApiField("url")
    private String url;


}
