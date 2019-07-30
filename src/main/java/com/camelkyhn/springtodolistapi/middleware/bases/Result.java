package com.camelkyhn.springtodolistapi.middleware.bases;

public class Result<T> {

    private String exceptionType;

    private String exceptionMessage;

    private Boolean succeeded;

    private T data;

    private Pagination pagination;

    public Result() {
    }

    public Result(String exceptionType, String exceptionMessage, Boolean succeeded, T data, Pagination pagination) {
        this.exceptionType = exceptionType;
        this.exceptionMessage = exceptionMessage;
        this.succeeded = succeeded;
        this.data = data;
        this.pagination = pagination;
    }

    public void Error(Exception exception) {
        this.succeeded = false;
        this.exceptionType = exception.getClass().getSimpleName();
        this.exceptionMessage = exception.getMessage();
    }

    public void Success(T data) {
        this.succeeded = true;
        this.data = data;
    }

    public void Success(T data, BaseFilterDto filterDto) {
        this.succeeded = true;
        this.pagination = new Pagination(filterDto);
        this.data = data;
    }

    public Result Map(Result resultSource, Result resultTarget) {
        resultTarget.exceptionType = resultSource.exceptionType;
        resultTarget.exceptionMessage = resultSource.exceptionMessage;
        resultTarget.succeeded = resultSource.succeeded;
        return resultTarget;
    }

    public String getExceptionType() {
        return exceptionType;
    }

    public void setExceptionType(String exceptionType) {
        this.exceptionType = exceptionType;
    }

    public String getExceptionMessage() {
        return exceptionMessage;
    }

    public void setExceptionMessage(String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }

    public Boolean getSucceeded() {
        return succeeded;
    }

    public void setSucceeded(Boolean succeeded) {
        this.succeeded = succeeded;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }
}