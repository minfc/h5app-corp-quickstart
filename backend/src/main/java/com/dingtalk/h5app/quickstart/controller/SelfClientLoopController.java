package com.dingtalk.h5app.quickstart.controller;

import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.*;
import com.dingtalk.api.response.*;
import com.dingtalk.h5app.quickstart.config.AppConfig;
import com.dingtalk.h5app.quickstart.config.UserDefineUrlConstant;
import com.dingtalk.h5app.quickstart.domain.entity.ProcessModuleVoEntity;
import com.dingtalk.h5app.quickstart.domain.ServiceResult;
import com.dingtalk.h5app.quickstart.service.ParseServivce;
import com.dingtalk.h5app.quickstart.service.TokenService;
import com.taobao.api.ApiException;
import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 微应用QuickStart示例，访问联系人API
 *
 * @author openapi@dingtalk
 * @date 2020/2/4
 */
@RestController
@CrossOrigin("*") // NOTE：此处仅为本地调试使用，为避免安全风险，生产环境请勿设置CORS为 '*'
public class SelfClientLoopController {
    private static final Logger log = LoggerFactory.getLogger(SelfClientLoopController.class);

    private TokenService tokenService;
    private AppConfig appConfig;
    @Autowired
    private Mapper dozerMapper;

    @Autowired
    public SelfClientLoopController(
        TokenService tokenService,
        AppConfig appConfig
    ) {
        this.tokenService = tokenService;
        this.appConfig = appConfig;
    }

    //后面转换为定时任务
    //http:127.0.0.1:8080/self/quarter
    @GetMapping("/self/quarter")
    public ServiceResult<String> timeQuarter() {
        String accessToken;
        // 获取accessToken
        ServiceResult<String> accessTokenSr = tokenService.getAccessToken();
        if (!accessTokenSr.isSuccess()) {
            return ServiceResult.failure(accessTokenSr.getCode(), accessTokenSr.getMessage());
        }
        accessToken = accessTokenSr.getResult();
        //String urlprcocessinstancelistidswithtoken = UserDefineUrlConstant.URL_PROCESSINSTANCE_LISTIDS + "?access_token=" + accessToken;
        getProcessModuleListByuserid(accessToken);
        try {
            DingTalkClient client = new DefaultDingTalkClient(UserDefineUrlConstant.URL_PROCESSINSTANCE_LISTIDS);
            OapiProcessinstanceListidsRequest req = new OapiProcessinstanceListidsRequest();
            //可以通过/process/listbyuserid，根据用户id获取可见审批模板列表.这个模板就是word的设计模板
            req.setProcessCode("PROC-735AEDFB-4179-4E08-889E-87DA12685A95");
            req.setStartTime(1496678400000L);//增加一个L
            OapiProcessinstanceListidsResponse rsp = client.execute(req, accessToken);//在这个地方增加accessToken
            List<String> instanceList=rsp.getResult().getList();
            for(String each :instanceList){
                handleEachInstance(each,accessToken);
            }
            System.out.println(rsp.getBody());
        } catch (ApiException e) {
            e.printStackTrace();
        }
        return ServiceResult.success(accessTokenSr.getMessage());
    }
    //https://open-dev.dingtalk.com/apiExplorer#/?devType=org&api=/process/listbyuserid
    private void getProcessModuleListByuserid(String accessToken) {
        try {
            DingTalkClient client = new DefaultDingTalkClient(UserDefineUrlConstant.URL_PROCESSLISTBYUSERID);
            OapiProcessListbyuseridRequest req = new OapiProcessListbyuseridRequest();
            req.setOffset(0L);
            req.setSize(100L);
            OapiProcessListbyuseridResponse rsp = client.execute(req, accessToken);
            OapiProcessListbyuseridResponse.HomePageProcessTemplateVo  homePageProcessTemplateVo = rsp.getResult();
            List<OapiProcessListbyuseridResponse.ProcessTopVo> list=homePageProcessTemplateVo.getProcessList();
            list.forEach(x->{

                ProcessModuleVoEntity processModuleVoEntity =dozerMapper.map(x,ProcessModuleVoEntity.class);
                System.out.println(processModuleVoEntity);
            });
            if(rsp.getResult().getNextCursor()==null) System.out.println("本次调用没有下一页");
            System.out.println(rsp.getBody());
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }

    private void handleEachInstance(String each,String accessToken) {
        try {
            DingTalkClient client = new DefaultDingTalkClient(UserDefineUrlConstant.URL_PROCESSINSTANCE_GET);
            OapiProcessinstanceGetRequest req = new OapiProcessinstanceGetRequest();
            req.setProcessInstanceId(each);
            OapiProcessinstanceGetResponse rsp = client.execute(req, accessToken);

            ParseServivce.parseInstance(rsp,dozerMapper);
            System.out.println(rsp.getBody());
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }
}
