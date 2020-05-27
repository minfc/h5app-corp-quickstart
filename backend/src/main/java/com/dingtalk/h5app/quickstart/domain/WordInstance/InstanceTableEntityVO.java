package com.dingtalk.h5app.quickstart.domain.WordInstance;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/*@Data
@ToString(callSuper = true)
@EqualsAndHashCode()*/
@Table(name = "instanceTableEntityVO", schema = "jeecg-boot-sjk", catalog = "")
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class InstanceTableEntityVO implements Serializable {
    @io.swagger.annotations.ApiModelProperty(value = "系统解析是自动生成ID")
    @Id
    @Column(name = "id")
    private String id;
    @io.swagger.annotations.ApiModelProperty(value = "表在文档中是顺序")
    private int tableSequence;



    private String table_name;
    @io.swagger.annotations.ApiModelProperty(value = "表格的内容")
    private String tableText;
    @io.swagger.annotations.ApiModelProperty(value = "表格的hashcode")
    private String tableHashcode;
    //@Transient
    @JsonIgnore
    private int loopRowIndex=-1;


    //在页面输入的时候，有这个值，在word模板解析的时候，没有这个值。
    @JsonIgnore
    private String tableNameFromPage;

    @JsonIgnoreProperties("instanceTableEntityVOS")
    @ManyToOne(cascade = CascadeType.ALL,optional = false,fetch = FetchType.LAZY)
    @JoinColumn(name = "wofid",foreignKey = @ForeignKey(name = "none",value = ConstraintMode.NO_CONSTRAINT))
    private InstanceWordEntityVO instanceWordEntityVO;

    @JsonIgnoreProperties("instanceTableEntityVO")
    @OneToMany(mappedBy = "instanceTableEntityVO",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Set<InstanceBookMarkEntityVO> instanceBookMarkEntityVOST =new HashSet<>();



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getTableSequence() {
        return tableSequence;
    }

    public void setTableSequence(int tableSequence) {
        this.tableSequence = tableSequence;
    }

    public String getTableText() {
        return tableText;
    }

    public void setTableText(String tableText) {
        this.tableText = tableText;
    }

    public String getTableHashcode() {
        return tableHashcode;
    }

    public void setTableHashcode(String tableHashcode) {
        this.tableHashcode = tableHashcode;
    }

    public InstanceWordEntityVO getInstanceWordEntityVO() {
        return instanceWordEntityVO;
    }

    public void setInstanceWordEntityVO(InstanceWordEntityVO instanceWordEntityVO) {
        this.instanceWordEntityVO = instanceWordEntityVO;
    }

    public int getLoopRowIndex() {
        return loopRowIndex;
    }

    public void setLoopRowIndex(int loopRowIndex) {
        this.loopRowIndex = loopRowIndex;
    }



    public Set<InstanceBookMarkEntityVO> getInstanceBookMarkEntityVOS() {
        return instanceBookMarkEntityVOST;
    }

    public void setInstanceBookMarkEntityVOS(Set<InstanceBookMarkEntityVO> instanceBookMarkEntityVOS) {
        this.instanceBookMarkEntityVOST = instanceBookMarkEntityVOS;
    }

    public String getTableNameFromPage() {
        return tableNameFromPage;
    }

    public void setTableNameFromPage(String tableNameFromPage) {
        this.tableNameFromPage = tableNameFromPage;
    }
    public String getTable_name() {
        return table_name;
    }

    public void setTable_name(String table_name) {
        this.table_name = table_name;
    }
}
