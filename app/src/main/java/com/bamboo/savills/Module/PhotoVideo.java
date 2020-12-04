package com.bamboo.savills.Module;

public class PhotoVideo {

    /**
     * id : 97959320-dfd9-465b-8288-08d8966d62db
     * fileName : img_floor_1.png
     * fileType : 0
     * createdBy : 10079
     * createdOn : 2020-12-02T02:53:02.36
     * createdByUserName : Sunny
     */

    private String id;
    private String fileName;
    private int fileType;
    private int createdBy;
    private String createdOn;
    private String createdByUserName;
    private String newName;

    public String getNewName() {
        return newName;
    }

    public void setNewName(String newName) {
        this.newName = newName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getFileType() {
        return fileType;
    }

    public void setFileType(int fileType) {
        this.fileType = fileType;
    }

    public int getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    public String getCreatedByUserName() {
        return createdByUserName;
    }

    public void setCreatedByUserName(String createdByUserName) {
        this.createdByUserName = createdByUserName;
    }
}
