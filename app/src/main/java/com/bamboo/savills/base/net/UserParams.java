package com.bamboo.savills.base.net;

/**
 * Created by qyj on 2019/10/9.
 */

public class UserParams {
    private String userCode;

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    private UserParams() {
    }

    public static UserParams getInstance() {
        return UserParamsHolder.instance;
    }

    private static class UserParamsHolder {
        private static UserParams instance = new UserParams();
    }


}
