package com.bamboo.savills.Module;

import com.chad.library.adapter.base.entity.JSectionEntity;

public class PartBSection extends JSectionEntity {
    private boolean isHeader;
    private PartBInfo info;

    public PartBSection(boolean isHeader, PartBInfo info) {
        this.isHeader = isHeader;
        this.info = info;
    }

    public void setHeader(boolean header) {
        isHeader = header;
    }

    public PartBInfo getInfo() {
        return info;
    }

    public void setInfo(PartBInfo info) {
        this.info = info;
    }

    @Override
    public boolean isHeader() {
        return isHeader;
    }
}
