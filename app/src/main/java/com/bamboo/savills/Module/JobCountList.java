package com.bamboo.savills.Module;

import java.util.List;

public class JobCountList {

    /**
     * data : [{"item1":"AssignedToMe","item2":0},{"item1":"Completed","item2":0},{"item1":"OnHold","item2":0},{"item1":"Unassigned","item2":6},{"item1":"Accepted","item2":0}]
     * code : 0
     * codeDesc : OK
     */

    private int code;
    private String codeDesc;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getCodeDesc() {
        return codeDesc;
    }

    public void setCodeDesc(String codeDesc) {
        this.codeDesc = codeDesc;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * item1 : AssignedToMe
         * item2 : 0
         */

        private String item1;
        private int item2;

        public String getItem1() {
            return item1;
        }

        public void setItem1(String item1) {
            this.item1 = item1;
        }

        public int getItem2() {
            return item2;
        }

        public void setItem2(int item2) {
            this.item2 = item2;
        }
    }
}
