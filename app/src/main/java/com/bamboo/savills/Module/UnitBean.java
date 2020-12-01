package com.bamboo.savills.Module;

public class UnitBean {

    private int id;
    private int jobId;
    private int originalPropertyBuildingUnitId;
    private String floor;
    private String unitNumber;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public int getOriginalPropertyBuildingUnitId() {
        return originalPropertyBuildingUnitId;
    }

    public void setOriginalPropertyBuildingUnitId(int originalPropertyBuildingUnitId) {
        this.originalPropertyBuildingUnitId = originalPropertyBuildingUnitId;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getUnitNumber() {
        return unitNumber;
    }

    public void setUnitNumber(String unitNumber) {
        this.unitNumber = unitNumber;
    }
}
