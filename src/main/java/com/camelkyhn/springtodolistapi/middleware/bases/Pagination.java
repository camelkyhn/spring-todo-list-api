package com.camelkyhn.springtodolistapi.middleware.bases;

public class Pagination {

    private Integer pageSize;
    private Integer pageNumber;
    private Long totalCount;

    public Pagination() {
    }

    public Pagination(Integer pageSize, Integer pageNumber, Long totalCount) {
        this.pageSize = pageSize;
        this.pageNumber = pageNumber;
        this.totalCount = totalCount;
    }

    public Pagination(BaseFilterDto filterDto) {
        this.pageSize = filterDto.getPageSize();
        this.pageNumber = filterDto.getPageNumber();
        this.totalCount = filterDto.getTotalCount();
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

    public Long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }
}