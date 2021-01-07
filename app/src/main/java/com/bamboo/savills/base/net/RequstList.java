package com.bamboo.savills.base.net;

/**
 * Created by qyj on 2019/9/29.
 */

public class RequstList {

    public static final String BASE_URL = "https://savillswebapi.azurewebsites.net";
    public static final String SCOPS = "api://15df8d38-ad1a-454f-ae12-edbce9f8f858/access_as_user";

//    dev
//    public static final String BASE_URL = "https://savillsdevvaluationcubeservice.azurewebsites.net";
//    public static final String SCOPS = "api://0e309d9a-e33d-470d-bbb3-6560163f5c54/access_as_user";
//    sit
//    public static final String BASE_URL = "https://savillssitvaluationcubeservice.azurewebsites.net";
//    public static final String SCOPS = "api://5d20513a-1b74-4de8-b51e-95020f718a1d/access_as_user";

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

    public static final String DELETE_IMGS_VIDEO = "/api/v1/InspectionForm/DeleteFromFile/";
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
//    保存FormA /api/v1/InspectionForm/SaveFormA/{jobId}
    public static final String SAVE_FORM_A = "/api/v1/InspectionForm/SaveFormA/";
//    /api/v1/InspectionForm/Submit/{jobId}/{jobPropertyId} 废弃
//    /api/v1/InspectionForm/SubmitFormA/{jobId}/{jobPropertyId}
    public static final String SUBMIT_FORM = "/api/v1/InspectionForm/SubmitFormA/";
//    /api/v1/InspectionForm/GetFormB/{jobId}/{jobPropertyId}/{formBId}
    public static final String GET_FORM_B_DETAIL = "/api/v1/InspectionForm/GetFormB/";
//    /api/v1/InspectionForm/UpdateFormB/{jobId}/{formBId}
    public static final String SAVE_FORM_B = "/api/v1/InspectionForm/UpdateFormB/";
// /api/v1/InspectionForm/SubmitFormB/{jobId}/{jobPropertyId}/{formBId}
    public static final String SUBMIT_FORM_B = "/api/v1/InspectionForm/SubmitFormB/";
//    /api/v1/InspectionForm/SubmitInspection/{jobId}/{jobPropertyId}
    public static final String SUBMIT_INSPECTION_FORM = "/api/v1/InspectionForm/SubmitInspection/";

}

