package com.bms.dummyrestapi.core.utilities;

public class ErrorDataResult<T> extends DataResult<T> {
    private String code;

    public ErrorDataResult(T data) {
        super(data, false);
    }

    public ErrorDataResult(T data, String message) {
        super(data, false, message);
    }

    public ErrorDataResult(T data, String code, String message) {
        super(data, false, message);
        this.code = code;
    }

    public ErrorDataResult(String message) {
        super(null, false, message);
    }

    public ErrorDataResult() {
        super(null, false);
    }

    public String getCode() {
        return this.code;
    }

}