package com.dingtalk.h5app.quickstart.domain.WordInstance;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import java.io.Serializable;



/*@Data*/
@Entity
@Table(name = "instanceImageValueEntityVO")
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class InstanceImageValueEntityVO implements Serializable {
    @ApiModelProperty(value = "解析过程中系统自动生成的ID")
    @Id
    @Column(name = "id")
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getDataofimagestring() {
        return dataofimagestring;
    }

    public void setDataofimagestring(String dataofimagestring) {
        this.dataofimagestring = dataofimagestring;
    }

    @ApiModelProperty(value = "*文档中要求插入的图片的宽带")
    //限制必须为一个小数，且整数部分的位数不能超过integer，小数部分的位数不能超过fraction
    @Digits(integer = 5,fraction = 0,message ="数字在5位数之内，并且小数位数为0" )

    private int          width;
    @ApiModelProperty(value = "*文档中要求插入的图片的高度")
    @Digits(integer = 5,fraction = 0,message ="数字在5位数之内，并且小数位数为0" )
    // 图片高度
    private int          height;
    // 图片信息
    //@NotBlank(message ="图片二进制采用Base64转换所产生的String不能为空")
    @ApiModelProperty(value = "*图片的二进制转为字符串")
    @Lob
    private String dataofimagestring;

    public int getAfterParseHeight(String currentText){
        JSONObject jsonObject=handleCurrentText(currentText);

        return Integer.parseInt(jsonObject.get("高度").toString());

    }
    public int getAfterParseWidth(String currentText){
        JSONObject jsonObject=handleCurrentText(currentText);
        return Integer.parseInt(jsonObject.get("宽度").toString());
    }
    private JSONObject handleCurrentText(String currentText){
        currentText = currentText.substring(currentText.indexOf("{{") + 2, currentText.indexOf("}}"));
        currentText=currentText.substring(currentText.indexOf("(")+1,currentText.indexOf(")"));
        currentText="{"+currentText+"}";
        System.out.println("打印出图片中的字段内容"+currentText+"\r\n");
        currentText = currentText.replace("“", "\"").replace("”", "\"");
        JSONObject jsonObject2 =JSONObject.parseObject(currentText);
        return jsonObject2;

    }

}
