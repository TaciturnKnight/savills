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
        private List<PartBAnswer> formBs;

        public String getFileName() {
            return fileName;
        }

        public void setFileName(String fileName) {
            this.fileName = fileName;
        }

        public List<PartBAnswer> getFormBs() {
            return formBs;
        }

        public void setFormBs(List<PartBAnswer> formBs) {
            this.formBs = formBs;
        }
    }
}
