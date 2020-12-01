package com.bamboo.savills.Module;

import java.util.List;

public class JobModule {
    private String name = "S9 Florissa Park Giffard Mansion";
//    是否展开 默认false
    private boolean isShow = false;
    private JobBean job;
    private String propertyName;
    private String street1;
    private String createdOn;
    private int id;
    private int jobId;
    private String inspectorAssignedOfficerUserName;
    private String ActualInspectionDate;
    private List<BuildingBean> buildings;
    private int siteVisitStatus;

    public int getSiteVisitStatus() {
        return siteVisitStatus;
    }

    public void setSiteVisitStatus(int siteVisitStatus) {
        this.siteVisitStatus = siteVisitStatus;
    }

    public List<BuildingBean> getBuildings() {
        return buildings;
    }

    public void setBuildings(List<BuildingBean> buildings) {
        this.buildings = buildings;
    }

    public String getActualInspectionDate() {
        return ActualInspectionDate;
    }

    public void setActualInspectionDate(String actualInspectionDate) {
        ActualInspectionDate = actualInspectionDate;
    }

    public String getInspectorAssignedOfficerUserName() {
        return inspectorAssignedOfficerUserName;
    }

    public void setInspectorAssignedOfficerUserName(String inspectorAssignedOfficerUserName) {
        this.inspectorAssignedOfficerUserName = inspectorAssignedOfficerUserName;
    }

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

    public JobBean getJob() {
        return job;
    }

    public void setJob(JobBean job) {
        this.job = job;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getStreet1() {
        return street1;
    }

    public void setStreet1(String street1) {
        this.street1 = street1;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    public boolean isShow() {
        return isShow;
    }

    public void setShow(boolean show) {
        isShow = show;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
