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
    public static final String JOB_LIST_NUM = "/api/v1/Mobile/GetDifferentTypeCount";
    public static final String JOB_ACCEPT = "/api/v1/Mobile/AcceptedSiteVisit/";
    public static final String JOB_UNASSIGN = "/api/v1/Mobile/UnassignToMe/";
    public static final String JOB_START_INSPECTION = "/api/v1/Mobile/StartInspection/";
//   post 上传 /api/v1/JobFile/Upload/{jobId} 弃用
    public static final String JOB_UPLOAD = "/api/v1/JobFile/Upload/";
//   GET /api/v1/JobFile/FileList/{jobId}
    public static final String JOB_GET_IMGS = "/api/v1/JobFile/FileList/";

    public static final String SHOW_IMGS_VIDEO = "/api/v1/JobFile/GetFile/";

    public static final String DELETE_IMGS_VIDEO = "/api/v1/JobFile/Delete/";
//    更新floor plan
    public static final String UPDATE_FLOOR_PLAN = "/api/v1/JobFile/UploadFloorPlan/";

    public static final String JOB_UPLOAD_FLOOR_PLAN = "/api/v1/JobFile/UploadFloorPlan/";

    public static final String CHANGE_FLOOR_PLAN_NAME = "/api/v1/JobFile/Rename/";
//    创建FormBs
    public static final String JOB_CREATE_FORM_B = "/api/v1/InspectionForm/CreateFormBs/";

    public static final String GET_FORM_B_LIST = "/api/v1/InspectionForm/GetFormBs/";
//    获取FormA 详情
    public static final String GET_FORM_A_DETAIL = "/api/v1/InspectionForm/GetFormA/";

//    /api/v1/InspectionForm/Upload/{jobId}/{formId}
//    上传图片 视频 表单提交
    public static final String UPLOAD_FORM_IMG_VIDEO = "/api/v1/InspectionForm/Upload/";
//    获取form对应的资源 /api/v1/InspectionForm/GetFormFileList/{jobId}/{formId}
    public static final String GET_FORM_FILE_LIST = "/api/v1/InspectionForm/GetFormFileList/";
//    获取form 图片 视频资源
    public static final String GET_FORM_VIDEO_IMG = "/api/v1/InspectionForm/GetFormFile/";



}

