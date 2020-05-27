package com.dingtalk.h5app.quickstart.config;

public class UserDefineUrlConstant {
    private static final String HOST = UrlConstant.HOST;
    /**
     * 根据用户id获取可见审批模板列表
     */
    public static final String URL_PROCESSLISTBYUSERID = HOST + "/topapi//process/listbyuserid";
    /**
     * 分页获取审批实例id列表
     */
    public static final String URL_PROCESSINSTANCE_LISTIDS = HOST + "/topapi/processinstance/listids";
    /**
     * 获取单个审批实例详情
     */
    public static final String URL_PROCESSINSTANCE_GET = HOST + "/topapi/processinstance/get";

}
