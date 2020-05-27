package com.dingtalk.h5app.quickstart.domain.WordInstance;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;

/*@Data
@ToString(callSuper = true)
@EqualsAndHashCode()*/
@Entity
@Table(name = "instanceMultiValueEntityVO", schema = "jeecg-boot-sjk", catalog = "")
@JsonIgnoreProperties(ignoreUnknown = true)
public class InstanceMultiValueEntityVO implements Serializable {
    @io.swagger.annotations.ApiModelProperty(value = "系统解析是自动生成ID")
    @Id
    @Column(name = "id")
    private  String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBookmarkvalue() {
        return bookmarkvalue;
    }

    public void setBookmarkvalue(String bookmarkvalue) {
        this.bookmarkvalue = bookmarkvalue;
    }

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    public InstanceImageValueEntityVO getInstanceImageValueEntityVO() {
        return instanceImageValueEntityVO;
    }

    public void setInstanceImageValueEntityVO(InstanceImageValueEntityVO instanceImageValueEntityVO) {
        this.instanceImageValueEntityVO = instanceImageValueEntityVO;
    }

    public InstanceBookMarkEntityVO getInstanceBookMarkEntityVO() {
        return instanceBookMarkEntityVO;
    }

    public void setInstanceBookMarkEntityVO(InstanceBookMarkEntityVO instanceBookMarkEntityVO) {
        this.instanceBookMarkEntityVO = instanceBookMarkEntityVO;
    }
    @ApiModelProperty(value = "*多值时普通字段的值")
    private  String bookmarkvalue;
    @ApiModelProperty(value = "*普通字段循环的值，从1开始，决定行中的顺序")
    private  int sequence;
    @ApiModelProperty(value = "*图片字段循环的值，从1开始，决定行中的顺序")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "multiimagevalue_id")
    private InstanceImageValueEntityVO instanceImageValueEntityVO;
    @JsonIgnoreProperties("instanceMultiValueEntityVOS")
    @ManyToOne(cascade = CascadeType.ALL,optional = false,fetch = FetchType.LAZY)
    @JoinColumn(name = "fid"/*,foreignKey = @ForeignKey(name = "none",value = ConstraintMode.NO_CONSTRAINT)*/)
    private InstanceBookMarkEntityVO instanceBookMarkEntityVO;
}
