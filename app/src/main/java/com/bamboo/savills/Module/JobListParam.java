package com.bamboo.savills.Module;

public class JobListParam {
    private int pageNumber;
    private int pageSize;
    private String searchValue;
//    orderByColumnName为propertyName，orderByDirection为ascend或者descend
    private String orderByColumnName;
    private String orderByDirection = "ascend";

    public String getOrderByColumnName() {
        return orderByColumnName;
    }

    public void setOrderByColumnName(String orderByColumnName) {
        this.orderByColumnName = orderByColumnName;
    }

    public String getOrderByDirection() {
        return orderByDirection;
    }

    public void setOrderByDirection(String orderByDirection) {
        this.orderByDirection = orderByDirection;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getSearchValue() {
        return searchValue;
    }

    public void setSearchValue(String searchValue) {
        this.searchValue = searchValue;
    }
}
