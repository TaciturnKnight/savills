package com.bamboo.savills.Module;

import java.util.List;

public class PhotoVideoList {

     private List<PhotoVideo> data;
     private int code;
     private String codeDesc;

    public List<PhotoVideo> getData() {
        return data;
    }

    public void setData(List<PhotoVideo> data) {
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
}
