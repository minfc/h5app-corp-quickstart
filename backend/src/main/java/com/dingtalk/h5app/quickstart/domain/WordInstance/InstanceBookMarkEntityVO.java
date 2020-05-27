package com.dingtalk.h5app.quickstart.domain.WordInstance;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/*@Data
@ToString(callSuper = true)
@EqualsAndHashCode()*/
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class  InstanceBookMarkEntityVO implements Serializable    {
    @io.swagger.annotations.ApiModelProperty(value = "系统解析是自动生成ID")
    @Id
    @Column(name = "id")
    private String id;
    private String bookMarkFromHFPC;//书签来源只能在Header，Footer，Paragraph或Cell
    @JsonIgnore
    @io.swagger.annotations.ApiModelProperty(value = "段落中的标签顺序，从0开始")
    private int bookmarkNoOfPara;
    @io.swagger.annotations.ApiModelProperty(value = "标签类型，计算字段或普通字段now(),le,fe:,p是属于计算字段，用户不要赋值")
    private String bookmarkType;//计算字段，普通字段
    @io.swagger.annotations.ApiModelProperty(value = "书签名")
    @NotBlank(message ="书签不能为空")
    private String bookmarkname;
    private int bookmarknamehashcode;
    @io.swagger.annotations.ApiModelProperty(value = "*书签值，如果是普通字段，用户填写这个值。如果是计算字段用户不填，如果是计算字段的图片，填写imageValueEntityVO中的值")
    private String bookmarkvalue;//在解析文档的时候，不需要这个多值
    @JsonIgnore
    @Transient
    private int bookvalueCount;//暂时没有用
    @io.swagger.annotations.ApiModelProperty(value = "书签单值时，且属于图片时，系统自动生成ID")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "imagevalue_id")
    private InstanceImageValueEntityVO instanceImageValueEntityVO;//单值的时候从这个地方取,与bookmarkvalue是2个选择一个


    @io.swagger.annotations.ApiModelProperty(value = "书签循环多值的时候，填写")
    //@JsonIgnore
    @JsonIgnoreProperties("instanceBookMarkEntityVO")
    @OneToMany(mappedBy = "instanceBookMarkEntityVO",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Set<InstanceMultiValueEntityVO> instanceMultiValueEntityVOS =new HashSet<>();//因为在解析文档的时候，不需要这个多值
    //然后在读入Excel的数据的时候，肯定设置

    //双向配置Jpa
    @JsonIgnoreProperties("instanceBookMarkEntityVOS")
    @ManyToOne(cascade = CascadeType.ALL,optional = false,fetch = FetchType.LAZY)
    @JoinColumn(name = "wfid"/*,foreignKey = @ForeignKey(name = "none",value = ConstraintMode.NO_CONSTRAINT)*/)
    private InstanceWordEntityVO instanceWordEntityVO;

    //双向配置Jpa
    @JsonIgnoreProperties("instanceBookMarkEntityVOST")
    @ManyToOne(cascade = CascadeType.ALL,optional = false,fetch = FetchType.LAZY)
    @JoinColumn(name = "tfid"/*,foreignKey = @ForeignKey(name = "none",value = ConstraintMode.NO_CONSTRAINT)*/)
    private InstanceTableEntityVO instanceTableEntityVO;


    @JsonIgnore
    @Transient
    private boolean isFromPage=true;


}
