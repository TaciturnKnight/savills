package com.bamboo.savills.Module;

import java.util.List;

public class JobList {

    private DataBean data;
    private int code;
    private String codeDesc;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

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

    public static class DataBean {
        private int totalNumber;
        private int pageNumber;
        private int pageSize;
        private List<JobModule> result;

        public List<JobModule> getResult() {
            return result;
        }

        public void setResult(List<JobModule> result) {
            this.result = result;
        }

        public int getTotalNumber() {
            return totalNumber;
        }

        public void setTotalNumber(int totalNumber) {
            this.totalNumber = totalNumber;
        }

        public int getPageNumber() {
            return pageNumber;
        }

        public void setPageNumber(int pageNumber) {
            this.pageNumber = pageNumber;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }
    }
}
