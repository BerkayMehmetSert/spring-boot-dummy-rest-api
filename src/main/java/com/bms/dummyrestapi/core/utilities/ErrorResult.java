package com.bms.dummyrestapi.core.utilities;

public class ErrorResult extends Result {
    private String code;

    public ErrorResult() {
        super(false);
    }

    public ErrorResult(String message) {
        super(false, message);
    }

    public ErrorResult(String code, String message) {
        super(false, message);
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }
}
