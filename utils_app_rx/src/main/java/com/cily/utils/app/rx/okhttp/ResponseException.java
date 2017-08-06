package com.cily.utils.app.rx.okhttp;

/**
 * user:cily
 * time:2017/2/22
 * desc:
 */

public class ResponseException extends RuntimeException {
    private String errorCode;

    public ResponseException(String message) {
        this(message, null);
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public ResponseException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }
}
