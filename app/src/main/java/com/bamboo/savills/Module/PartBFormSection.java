package com.bamboo.savills.Module;

import com.chad.library.adapter.base.entity.JSectionEntity;

public class PartBFormSection extends JSectionEntity {

    private PartBAnswer formBsBean;
    private boolean isHeader;

    public PartBAnswer getFormBsBean() {
        return formBsBean;
    }

    public void setFormBsBean(PartBAnswer formBsBean) {
        this.formBsBean = formBsBean;
    }

    public boolean isHeader() {
        return isHeader;
    }

    public void setHeader(boolean header) {
        isHeader = header;
    }

    public PartBFormSection(PartBAnswer formBsBean, boolean isHeader) {
        this.formBsBean = formBsBean;
        this.isHeader = isHeader;
    }
}
