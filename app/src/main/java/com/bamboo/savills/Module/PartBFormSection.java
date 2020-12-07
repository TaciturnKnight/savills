package com.bamboo.savills.Module;

import com.chad.library.adapter.base.entity.JSectionEntity;

public class PartBFormSection extends JSectionEntity {

    private FormBList.DataBean.FormBsBean formBsBean;
    private boolean isHeader;

    public FormBList.DataBean.FormBsBean getFormBsBean() {
        return formBsBean;
    }

    public void setFormBsBean(FormBList.DataBean.FormBsBean formBsBean) {
        this.formBsBean = formBsBean;
    }

    public boolean isHeader() {
        return isHeader;
    }

    public void setHeader(boolean header) {
        isHeader = header;
    }

    public PartBFormSection(FormBList.DataBean.FormBsBean formBsBean, boolean isHeader) {
        this.formBsBean = formBsBean;
        this.isHeader = isHeader;
    }
}
