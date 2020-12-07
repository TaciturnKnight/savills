package com.bamboo.savills.Module;

import java.util.List;

public class FormBList {

    /**
     * data : [{"fileName":"img_floor_10.png","formBs":[{"id":"788f4da9-7b82-4fa8-d46b-08d898472370","jobPropertyId":1,"jobFileId":"50b292b9-f66d-4599-7355-08d896bb6d6d","title":"Bath","question12Accomdation":null,"question13InternalCondition":0,"question13InternalConditionRemark":null,"question14UnazuthrizedStructureOrAlteration":null,"question15View":0,"question15Aspect":0,"question16Usage":null,"question17CockloftRemark":null,"question17FlatRoofRemark":null,"question17GardenRemark":null,"question17YardRemark":null,"question17OthersRemark":null,"question18OccupationStatus":0,"question18OccupationDetails":null,"question19Remarks":null,"isSubmitted":false,"createdBy":10079,"createdOn":"2020-12-04T11:24:17.68","updatedBy":null,"updatedOn":"2020-12-04T11:24:18.923","createdByUserName":"","updatedByUserName":"","jobFileName":"img_floor_10.png"},{"id":"147e6817-89bc-4205-d46c-08d898472370","jobPropertyId":1,"jobFileId":"50b292b9-f66d-4599-7355-08d896bb6d6d","title":"SerR","question12Accomdation":null,"question13InternalCondition":0,"question13InternalConditionRemark":null,"question14UnazuthrizedStructureOrAlteration":null,"question15View":0,"question15Aspect":0,"question16Usage":null,"question17CockloftRemark":null,"question17FlatRoofRemark":null,"question17GardenRemark":null,"question17YardRemark":null,"question17OthersRemark":null,"question18OccupationStatus":0,"question18OccupationDetails":null,"question19Remarks":null,"isSubmitted":false,"createdBy":10079,"createdOn":"2020-12-04T11:24:17.69","updatedBy":null,"updatedOn":"2020-12-04T11:24:19","createdByUserName":"","updatedByUserName":"","jobFileName":"img_floor_10.png"}]}]
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
         * fileName : img_floor_10.png
         * formBs : [{"id":"788f4da9-7b82-4fa8-d46b-08d898472370","jobPropertyId":1,"jobFileId":"50b292b9-f66d-4599-7355-08d896bb6d6d","title":"Bath","question12Accomdation":null,"question13InternalCondition":0,"question13InternalConditionRemark":null,"question14UnazuthrizedStructureOrAlteration":null,"question15View":0,"question15Aspect":0,"question16Usage":null,"question17CockloftRemark":null,"question17FlatRoofRemark":null,"question17GardenRemark":null,"question17YardRemark":null,"question17OthersRemark":null,"question18OccupationStatus":0,"question18OccupationDetails":null,"question19Remarks":null,"isSubmitted":false,"createdBy":10079,"createdOn":"2020-12-04T11:24:17.68","updatedBy":null,"updatedOn":"2020-12-04T11:24:18.923","createdByUserName":"","updatedByUserName":"","jobFileName":"img_floor_10.png"},{"id":"147e6817-89bc-4205-d46c-08d898472370","jobPropertyId":1,"jobFileId":"50b292b9-f66d-4599-7355-08d896bb6d6d","title":"SerR","question12Accomdation":null,"question13InternalCondition":0,"question13InternalConditionRemark":null,"question14UnazuthrizedStructureOrAlteration":null,"question15View":0,"question15Aspect":0,"question16Usage":null,"question17CockloftRemark":null,"question17FlatRoofRemark":null,"question17GardenRemark":null,"question17YardRemark":null,"question17OthersRemark":null,"question18OccupationStatus":0,"question18OccupationDetails":null,"question19Remarks":null,"isSubmitted":false,"createdBy":10079,"createdOn":"2020-12-04T11:24:17.69","updatedBy":null,"updatedOn":"2020-12-04T11:24:19","createdByUserName":"","updatedByUserName":"","jobFileName":"img_floor_10.png"}]
         */

        private String fileName;
        private List<FormBsBean> formBs;

        public String getFileName() {
            return fileName;
        }

        public void setFileName(String fileName) {
            this.fileName = fileName;
        }

        public List<FormBsBean> getFormBs() {
            return formBs;
        }

        public void setFormBs(List<FormBsBean> formBs) {
            this.formBs = formBs;
        }

        public static class FormBsBean {
            /**
             * id : 788f4da9-7b82-4fa8-d46b-08d898472370
             * jobPropertyId : 1
             * jobFileId : 50b292b9-f66d-4599-7355-08d896bb6d6d
             * title : Bath
             * question12Accomdation : null
             * question13InternalCondition : 0
             * question13InternalConditionRemark : null
             * question14UnazuthrizedStructureOrAlteration : null
             * question15View : 0
             * question15Aspect : 0
             * question16Usage : null
             * question17CockloftRemark : null
             * question17FlatRoofRemark : null
             * question17GardenRemark : null
             * question17YardRemark : null
             * question17OthersRemark : null
             * question18OccupationStatus : 0
             * question18OccupationDetails : null
             * question19Remarks : null
             * isSubmitted : false
             * createdBy : 10079
             * createdOn : 2020-12-04T11:24:17.68
             * updatedBy : null
             * updatedOn : 2020-12-04T11:24:18.923
             * createdByUserName :
             * updatedByUserName :
             * jobFileName : img_floor_10.png
             */

            private String id;
            private int jobPropertyId;
            private String jobFileId;
            private String title;
            private int question13InternalCondition;
            private int question15View;
            private int question15Aspect;
            private int question18OccupationStatus;
            private boolean isSubmitted;
            private int createdBy;
            private String createdOn;
            private String updatedOn;
            private String createdByUserName;
            private String updatedByUserName;
            private String jobFileName;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public int getJobPropertyId() {
                return jobPropertyId;
            }

            public void setJobPropertyId(int jobPropertyId) {
                this.jobPropertyId = jobPropertyId;
            }

            public String getJobFileId() {
                return jobFileId;
            }

            public void setJobFileId(String jobFileId) {
                this.jobFileId = jobFileId;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }



            public int getQuestion13InternalCondition() {
                return question13InternalCondition;
            }

            public void setQuestion13InternalCondition(int question13InternalCondition) {
                this.question13InternalCondition = question13InternalCondition;
            }



            public int getQuestion15View() {
                return question15View;
            }

            public void setQuestion15View(int question15View) {
                this.question15View = question15View;
            }

            public int getQuestion15Aspect() {
                return question15Aspect;
            }

            public void setQuestion15Aspect(int question15Aspect) {
                this.question15Aspect = question15Aspect;
            }


            public int getQuestion18OccupationStatus() {
                return question18OccupationStatus;
            }

            public void setQuestion18OccupationStatus(int question18OccupationStatus) {
                this.question18OccupationStatus = question18OccupationStatus;
            }


            public boolean isIsSubmitted() {
                return isSubmitted;
            }

            public void setIsSubmitted(boolean isSubmitted) {
                this.isSubmitted = isSubmitted;
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



            public String getUpdatedOn() {
                return updatedOn;
            }

            public void setUpdatedOn(String updatedOn) {
                this.updatedOn = updatedOn;
            }

            public String getCreatedByUserName() {
                return createdByUserName;
            }

            public void setCreatedByUserName(String createdByUserName) {
                this.createdByUserName = createdByUserName;
            }

            public String getUpdatedByUserName() {
                return updatedByUserName;
            }

            public void setUpdatedByUserName(String updatedByUserName) {
                this.updatedByUserName = updatedByUserName;
            }

            public String getJobFileName() {
                return jobFileName;
            }

            public void setJobFileName(String jobFileName) {
                this.jobFileName = jobFileName;
            }
        }
    }
}
