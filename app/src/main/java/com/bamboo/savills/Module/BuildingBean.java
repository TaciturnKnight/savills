package com.bamboo.savills.Module;

import java.util.List;

public class BuildingBean {

    private int id;
    private int jobId;
    private int originalPropertyBuildingId;
    private String buildingName;
    private String buildingTypes;
    private List<UnitBean> units;

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

    public int getOriginalPropertyBuildingId() {
        return originalPropertyBuildingId;
    }

    public void setOriginalPropertyBuildingId(int originalPropertyBuildingId) {
        this.originalPropertyBuildingId = originalPropertyBuildingId;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public String getBuildingTypes() {
        return buildingTypes;
    }

    public void setBuildingTypes(String buildingTypes) {
        this.buildingTypes = buildingTypes;
    }

    public List<UnitBean> getUnits() {
        return units;
    }

    public void setUnits(List<UnitBean> units) {
        this.units = units;
    }
}
