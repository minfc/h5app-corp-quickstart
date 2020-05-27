package com.dingtalk.h5app.quickstart.service;

import com.alibaba.fastjson.JSONObject;
import com.dingtalk.api.response.OapiProcessinstanceGetResponse;
import com.dingtalk.h5app.quickstart.domain.WordInstance.InstanceBookMarkEntityVO;
import com.dingtalk.h5app.quickstart.domain.WordInstance.InstanceTableEntityVO;
import com.dingtalk.h5app.quickstart.domain.entity.ProcessInstanceEntity;
import com.dingtalk.h5app.quickstart.domain.entity.RowValue;
import com.dingtalk.h5app.quickstart.domain.entity.RowVoEntity;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.dozer.Mapper;

import java.util.List;
import java.util.UUID;

public class ParseServivce {
    public static void parseInstance(OapiProcessinstanceGetResponse rsp,Mapper dozerMapper) {
        OapiProcessinstanceGetResponse.ProcessInstanceTopVo processTopVo=rsp.getProcessInstance();
        ProcessInstanceEntity processInstanceEntity =dozerMapper.map(processTopVo,ProcessInstanceEntity.class);
        System.out.println("当前的instance:"+ processInstanceEntity.toString());

        List<OapiProcessinstanceGetResponse.FormComponentValueVo> formComponentValueVoEntities = processTopVo.getFormComponentValues();
        for(OapiProcessinstanceGetResponse.FormComponentValueVo formComponentValueVo:formComponentValueVoEntities){
            if(formComponentValueVo.getComponentType().equalsIgnoreCase("TableField")){
                handleTable(formComponentValueVo);
            }else {
                handleNotTable(formComponentValueVo);
            }
        }


    }

    private static void handleNotTable(OapiProcessinstanceGetResponse.FormComponentValueVo formComponentValueVo) {
        if(formComponentValueVo.getComponentType().equalsIgnoreCase("DDPhotoField")){
            String jsonString=formComponentValueVo.getValue();
            jsonString=jsonString.replace("\\","");
            jsonString=jsonString.substring(2,jsonString.length()-2);
            System.out.println("JSONSTring:"+jsonString);
            InstanceBookMarkEntityVO instanceBookMarkEntityVO=new InstanceBookMarkEntityVO();
            instanceBookMarkEntityVO.setId(UUID.randomUUID().toString());
            instanceBookMarkEntityVO.setBookmarkname(formComponentValueVo.getName());
            instanceBookMarkEntityVO.setBookmarkvalue(jsonString);
        }else {

        }
    }

    private static void handleTable(OapiProcessinstanceGetResponse.FormComponentValueVo formComponentValueVo) {

        String jsonString=formComponentValueVo.getValue();
        jsonString=jsonString.replace("\\","");
        ObjectMapper objectMapper=new ObjectMapper();
        try {
            JSONObject jsonObject1 =JSONObject.parseObject(jsonString);
            //Object xx=jsonObject1.get("rowValue");
            List<RowValue> rowValues = objectMapper.readValue(jsonString, new TypeReference<List<RowValue>>() {
            });
            handleTable(rowValues);
        }catch (Exception ex){
            System.out.println(ex.toString());
        }


    }

    private static void handleTable(List<RowValue> rowValues) {
        InstanceTableEntityVO instanceTableEntityVO=new InstanceTableEntityVO();
        instanceTableEntityVO.setId(UUID.randomUUID().toString());

        //继续写Code
    }
}
