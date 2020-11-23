package com.bamboo.savills.base.net;

/**
 * Created by qyj on 2019/9/29.
 */

public class RequstList {
////    在使用什么环境 0 dev 1 uat 2 pro
//    public static final int IN_WHAT_ENVIROMENT = 2;
////    针对yict uat 环境 如果是url 就是true ip 为false（一般情况为false）
//    public static final boolean isURL = false;
////    是否为hit 的包 hit 包 不用配置isURL
//    public static final boolean isHIT = false;

    public static final String BASE_URL = "https://savillswebapi.azurewebsites.net";
    public static final String JOB_LIST = "api/v1/Job/MobileJobList";
    public static final String JOB_LIST_UNASSIGNED = "/api/v1/Mobile/GetUnassignedSiteVisit";
    public static final String JOB_LIST_ASSIGNEDTOME = "/api/v1/Mobile/GetAssignedToMeSiteVisit";
    public static final String JOB_LIST_ACCPET = "/api/v1/Mobile/GetAcceptedSiteVisit";
    public static final String JOB_LIST_COMPLETE = "/api/v1/Mobile/GetCompletedSiteVisit";
    public static final String JOB_LIST_ONHOLD = "/api/v1/Mobile/GetOnHoldSiteVisit";

}

