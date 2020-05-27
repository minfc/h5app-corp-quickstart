package com.dingtalk.h5app.quickstart.domain.WordInstance;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/*
@Data
@ToString(callSuper = true)
@EqualsAndHashCode()*/
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
// Spring 数据验证注解的使用 https://blog.csdn.net/u012693530/article/details/80831408
@Table(name = "instanceWordEntityVO", schema = "jeecg-boot-sjk", catalog = "")
public class InstanceWordEntityVO implements Serializable {

    @io.swagger.annotations.ApiModelProperty(value = "解析过程中自动生成的guid")
    @Id
    @Column(name = "id")
    private String id;
    private String processinstanceid;
    private String processinstancename;
    @ApiModelProperty(value = "客户端json文件的id")
    private String jsonfileid;
    @ApiModelProperty(value = "客户端简道云的id")
    @JsonIgnore
    private String jiangdaoyunid;
    @ApiModelProperty(value = "客户端钉钉的id")
    @JsonIgnore
    private String dingdingid;
    @ApiModelProperty(value = "文件合并的ID，如果是循环的文件，合并的id是一样的guid，如果不是循环文件，本字段为null")
    @JsonIgnore
    //暂时不需要这个属性
    private String comibineid;
    @ApiModelProperty(value = "文件来源，来源Excel或钉钉或简道云")
    private String filesource;
    @ApiModelProperty(value = "文件ID，每个文件服务器独立产生一个ID")
    private String fileid;//=System.currentTimeMillis();//时间点作为版本号

    private String sequenceno;
    @JsonIgnore
    @ApiModelProperty(value = "word模板字段，默认是空,是模板，或否")
    private String iswordModule;
    @ApiModelProperty(value = "word内容在json文件中的位置，根据这个位置来生成合并文档的顺序")
    private int wordSequence;
    @ApiModelProperty(value = "客户端的文件名")
    private String filename;
    @JsonIgnore
    private String filepath;//
    @ApiModelProperty(value = "客户端的文件后缀名")
    private String filesuffixname;
    /*@Max(value=10485760,message = "文件大小必须小于10M=10*1024*1024")
    @Min(value=1024,message = "文件大小必须大于1K")*/
    //@Size(max=10485760, min=1024,message ="文件大小必须大于1K,小于10M=10*1024*1024" )
    @ApiModelProperty(value = "客户端的文件大小")
    private Long filesize;
    @ApiModelProperty(value = "客户端的文件的MD5码*")
    //@NotBlank(message = "文件的MD5不能为空")
    private String md5;
    @JsonIgnore
    private String username;
//    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm")
    @JsonIgnore //如果在在一个标签@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm")。@JsonIgnore 就会失效
    //属于Jackson同类型的标签，Jsonignore优先级低。不同类型的书签如验证标签与Jackson标签相互不影响
    private Date wordgeneratortime;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date wordClientCommitTime;
    @JsonIgnore
    private Date wordServerReceiveTime;
   /**创建人*/
    @JsonIgnore
    private String create_by;
    /**创建日期*/

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    //@JsonIgnore
    @JsonProperty(value="createtime")
    private Date create_time;
    /**更新人*/
    @JsonIgnore
    private String update_by;
    /**更新日期*/

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")//这个标签与Ignore不会冲突
    @JsonIgnore
    private Date update_time;
    /**所属部门*/
    @JsonIgnore
    private String sysOrgCode;

    @JsonIgnoreProperties("instanceWordEntityVO")
    @OneToMany(mappedBy = "instanceWordEntityVO",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    //@org.hibernate.annotations.ForeignKey(name = "none")
    private Set<InstanceBookMarkEntityVO> instanceBookMarkEntityVOS=new HashSet<>();

    @ApiModelProperty(value = "客户端的文件的表格，允许为空")
    @JsonIgnoreProperties("instanceWordEntityVO")
    @OneToMany(mappedBy = "instanceWordEntityVO",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    //@org.hibernate.annotations.ForeignKey(name = "none")
    private Set<InstanceTableEntityVO> instanceTableEntityVOS =new HashSet<>();




    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProcessinstanceid() {
        return processinstanceid;
    }

    public void setProcessinstanceid(String processinstanceid) {
        this.processinstanceid = processinstanceid;
    }

    public String getProcessinstancename() {
        return processinstancename;
    }

    public void setProcessinstancename(String processinstancename) {
        this.processinstancename = processinstancename;
    }

    public String getJsonfileid() {
        return jsonfileid;
    }

    public void setJsonfileid(String jsonfileid) {
        this.jsonfileid = jsonfileid;
    }

    public String getJiangdaoyunid() {
        return jiangdaoyunid;
    }

    public void setJiangdaoyunid(String jiangdaoyunid) {
        this.jiangdaoyunid = jiangdaoyunid;
    }

    public String getDingdingid() {
        return dingdingid;
    }

    public void setDingdingid(String dingdingid) {
        this.dingdingid = dingdingid;
    }

    public String getComibineid() {
        return comibineid;
    }

    public void setComibineid(String comibineid) {
        this.comibineid = comibineid;
    }

    public String getFilesource() {
        return filesource;
    }

    public void setFilesource(String filesource) {
        this.filesource = filesource;
    }

    public String getFileid() {
        return fileid;
    }

    public void setFileid(String fileid) {
        this.fileid = fileid;
    }

    public String getIswordModule() {
        return iswordModule;
    }

    public void setIswordModule(String iswordModule) {
        this.iswordModule = iswordModule;
    }

    public int getWordSequence() {
        return wordSequence;
    }

    public void setWordSequence(int wordSequence) {
        this.wordSequence = wordSequence;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public String getFilesuffixname() {
        return filesuffixname;
    }

    public void setFilesuffixname(String filesuffixname) {
        this.filesuffixname = filesuffixname;
    }

    public Long getFilesize() {
        return filesize;
    }

    public void setFilesize(Long filesize) {
        this.filesize = filesize;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getWordgeneratortime() {
        return wordgeneratortime;
    }

    public void setWordgeneratortime(Date wordgeneratortime) {
        this.wordgeneratortime = wordgeneratortime;
    }

    public Date getWordClientCommitTime() {
        return wordClientCommitTime;
    }

    public void setWordClientCommitTime(Date wordClientCommitTime) {
        this.wordClientCommitTime = wordClientCommitTime;
    }

    public Date getWordServerReceiveTime() {
        return wordServerReceiveTime;
    }

    public void setWordServerReceiveTime(Date wordServerReceiveTime) {
        this.wordServerReceiveTime = wordServerReceiveTime;
    }

    public String getCreate_by() {
        return create_by;
    }

    public void setCreate_by(String create_by) {
        this.create_by = create_by;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public String getUpdate_by() {
        return update_by;
    }

    public void setUpdate_by(String update_by) {
        this.update_by = update_by;
    }

    public Date getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }

    public String getSysOrgCode() {
        return sysOrgCode;
    }

    public void setSysOrgCode(String sysOrgCode) {
        this.sysOrgCode = sysOrgCode;
    }

    public Set<InstanceBookMarkEntityVO> getInstanceBookMarkEntityVOS() {
        getInstanceTableEntityVOS().forEach(instanceTableEntityVO -> {

        });
        return instanceBookMarkEntityVOS;
    }

    public void setInstanceBookMarkEntityVOS(Set<InstanceBookMarkEntityVO> instanceBookMarkEntityVOS) {
        this.instanceBookMarkEntityVOS = instanceBookMarkEntityVOS;
    }

    public Set<InstanceTableEntityVO> getInstanceTableEntityVOS() {
        return instanceTableEntityVOS;
    }

    public void setInstanceTableEntityVOS(Set<InstanceTableEntityVO> instanceTableEntityVOS) {
        this.instanceTableEntityVOS = instanceTableEntityVOS;
    }
    public String getSequenceno() {
        return sequenceno;
    }

    public void setSequenceno(String sequenceno) {
        this.sequenceno = sequenceno;
    }
}
/*
json字符串中的key应该与java对象的属性名相同
        java对象中属性如果为private，则需要显示生成getter/setter方法；如果属性为public，则可以不必写getter/setter方法
        java对象如果有自定义的构造方法，json字符串转换为java对象时会出错
        如果json字符串中的属性个数小于java对象中的属性个数，可以顺利转换，java中多的那个属性为null
        如果json字符串中出现java对象中没有的属性，则在将json转换为java对象时会报错：Unrecognized field, not marked as ignorable
        解决方法：

        在目标对象的类级别上添加注解：@JsonIgnoreProperties(ignoreUnknown = true)；

        java对象名和json中名不一致时解决方法：

        在目标对象的字段级别上添加注解：@JsonProperty(value = "name") */
