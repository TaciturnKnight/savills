package com.bamboo.savills.base.net;

/**
 * Created by qyj on 2019/9/29.
 */

public class RequstList {
//    在使用什么环境 0 dev 1 uat 2 pro
    public static final int IN_WHAT_ENVIROMENT = 2;
//    针对yict uat 环境 如果是url 就是true ip 为false（一般情况为false）
    public static final boolean isURL = false;
//    是否为hit 的包 hit 包 不用配置isURL
    public static final boolean isHIT = false;

    //YICT
//    public static final String BASE_URL = "http://sis.bamboonetworks.com/SIS";
//    YICT DEV
//    public static final String BASE_URL = "https://192.168.9.138:8443/SIS";
//    uat-1
//    public static final String BASE_URL = "https://sisuat.yict.com.cn/SIS";
//    uat-2
//    public static final String BASE_URL = "https://176.18.21.21:18433/SIS";
//    pro
    public static final String BASE_URL = "https://176.18.21.21:28433/SIS";

    //    HIT/T8
//    public static final String BASE_URL = "http://sis.bamboonetworks.com/HITSIS";
    //    HIT/T8 UAT
//    public static final String BASE_URL = "https://172.18.159.144:443/SIS";

    public static final String CHECK_PASSWORD = "/rest/fmxUserRest/checkUser.json";
    public static final String SESSION_AUTHENTICATION = "/framemax-security-login";
    public static final String PASSWORD_IS_OVERDUE = "/rest/fmxUserRest/passwordExpire.json";
    public static final String GET_SELECT_CODE = "/rest/commonQueryRest/getSelectCodeDatas.json";
    public static final String CHECK_GROUP = "/rest/checkGroupRest/save.json";
    public static final String ADD_UPDATE_TAG = "/rest/barcodeRest/save.json";
    public static final String SEARCH_TAG = "/rest/commonQueryRest/query.json";
    public static final String CHANGE_PSD = "/rest/fmxUserRest/changPassWord.json";
    public static final String DEL_TAG = "/rest/barcodeRest/deleteById.json";
    public static final String SAVE_TASK = "/rest/checkTaskRest/save.json";
    public static final String USER_INFO = "/rest/currentUserRest/get.json";
    public static final String GET_FILTER = "/rest/commonQueryRest/query.json";
    public static final String CHECK_DETAIL = "/rest/checkTaskRest/get.json";
    public static final String CHECK_USERS = "/rest/checkTaskRest/getCheckUsers.json";
    public static final String SEARCH_STALL_IN_CHECK = "/rest/checkTaskRest/getPossibleCheckUsers.json";
    public static final String ADD_CHECK_STAFF = "/rest/checkTaskRest/saveCheckUsers.json";
    public static final String DELETE_TASK = "/rest/checkTaskRest/cancel.json";
    public static final String GET_RISK_ASSESSMENTS = "/rest/checkTaskRest/getRiskAssessments.json";
    public static final String GET_RISK_HISTORY = "/rest/checkTaskRest/getRiskAssessmentHistory.json";
    public static final String CONFIRM_RISK_ASSESSMENTS = "/rest/checkTaskRest/ackRiskAssessments.json";
    public static final String CHECK_TASK_LIST = "/rest/checkTaskRest/getCheckTaskView.json";
    public static final String SCAN_BAR_CODE = "/rest/checkTaskRest/scanBarcode.json";
    public static final String GET_CONFIG = "/rest/sisParamConfigRest/getParamConfigs.json";
    public static final String UPLOAD_IMAGE = "/rest/fileUploadRest/upload.json";
    public static final String GET_TASK_DETAIL = "/rest/checkItemRest/getCheckItem.json";
    public static final String SAVE_TASK_DETAIL = "/rest/checkItemRest/saveCheckItem.json";
    public static final String SUBMIT_TASK_DETAIL = "/rest/checkItemRest/submitCheckItem.json";
    public static final String QUIT_TASK_DETAIL = "/rest/checkItemRest/releaseCheckItemLock.json";
    public static final String GET_REJECT_USER = "/rest/checkTaskRest/getPossibleRejectUsers.json";
    public static final String REVIEW = "/rest/checkTaskRest/review.json";
    public static final String GET_MANAGER_LIST = "/rest/checkTaskRest/getPossibleLeadUsers.json";
    public static final String SAVE_Image = "/rest/checkTaskRest/saveCheckTaskView.json";
    public static final String LOG_OUT = "/framemax-security-logout";
    public static final String LOCK_CHACK_ITEM = "/rest/checkItemRest/startWorkForCheckItem.json";
    public static final String IS_ANSWER_ALL_QUESTION = "/rest/checkItemRest/isFinishAllQuestions.json";
    public static final String IS_SUBMIT_ALL_QUESTION = "/rest/checkTaskRest/isAllCheckItemSubmitted.json";
    public static final String SUBMIT_ALL_FORM = "/rest/checkTaskRest/submitCheckTask.json";
    public static final String GET_READ_TIME = "/rest/sisParamConfigRest/getParamConfig.json";
    public static final String GET_SIZE_ALREADY = "/rest/checkTaskRest/getTotalCheckTaskPictureSize.json";
    public static final String GET_VERSION = "/rest/apkInfoRest/get.json";

}
