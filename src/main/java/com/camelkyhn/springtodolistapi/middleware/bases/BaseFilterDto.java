package com.camelkyhn.springtodolistapi.middleware.bases;

import com.camelkyhn.springtodolistapi.middleware.enums.Status;

import java.util.Map;

public class BaseFilterDto {

    private Integer pageSize = 5;

    private Integer pageNumber = 1;

    private Integer totalCount;

    private Boolean isAllData;

    private Status status;

    public BaseFilterDto() {
    }

    public BaseFilterDto(Boolean isAllData) {
        setAllData(isAllData);
    }

    public BaseFilterDto(Map<String, String[]> data) {
        setPageSize(getIntegerParameter(data, "pageSize"));
        setPageNumber(getIntegerParameter(data, "pageNumber"));
        setAllData(getBooleanParameter(data, "isAllData"));
        setStatus(Status.valueOf(getStringParameter(data, "status")));
    }

    public int getPageSize() {
        if (pageSize == null || pageSize <= 5) {
            return 5;
        } else {
            return pageSize;
        }
    }

    public void setPageSize(Integer pageSize) {
        if (pageSize == null || pageSize <= 5) {
            this.pageSize = 5;
        } else {
            this.pageSize = pageSize;
        }
    }

    public int getPageNumber() {
        if (pageNumber == null || pageNumber <= 1) {
            return 1;
        } else {
            return pageNumber;
        }
    }

    public void setPageNumber(Integer pageNumber) {
        if (pageNumber == null || pageNumber <= 1) {
            this.pageNumber = 1;
        } else {
            this.pageNumber = pageNumber;
        }
    }

    public boolean getAllData() {
        if (this.isAllData == null) {
            return false;
        } else {
            return this.isAllData;
        }
    }

    public void setAllData(Boolean allData) {
        if (allData == null) {
            this.isAllData = false;
        } else {
            this.isAllData = allData;
        }
    }

    public Integer getTotalCount() {
        return this.totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Integer getIntegerParameter(Map<String, String[]> data, String key) {
        if (!isDataNull(data, key)) {
            return Integer.valueOf(data.get(key)[0]);
        }
        return null;
    }

    public Long getLongParameter(Map<String, String[]> data, String key) {
        if (!isDataNull(data, key)) {
            return Long.valueOf(data.get(key)[0]);
        }
        return null;
    }

    public String getStringParameter(Map<String, String[]> data, String key) {
        if (!isDataNull(data, key)) {
            return data.get(key)[0];
        }
        return null;
    }

    public Boolean getBooleanParameter(Map<String, String[]> data, String key) {
        if (!isDataNull(data, key)) {
            return Boolean.valueOf(data.get(key)[0]);
        }
        return null;
    }

    public boolean isDataNull(Map<String, String[]> data, String key) {
        return data == null || data.get(key) == null;
    }
}
