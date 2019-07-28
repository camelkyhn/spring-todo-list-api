package com.camelkyhn.springtodolistapi.middleware.bases;

public class Pagination {

    private Integer pageSize;
    private Integer pageNumber;
    private Integer totalCount;

    public Pagination() {
    }

    public Pagination(Integer pageSize, Integer pageNumber, Integer totalCount) {
        this.pageSize = pageSize;
        this.pageNumber = pageNumber;
        this.totalCount = totalCount;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }
}