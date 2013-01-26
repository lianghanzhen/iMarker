package com.imarker.exception;

public class IMarkerException extends Exception {

    public IMarkerException() {}

    public IMarkerException(String detailMessage) {
        super(detailMessage);
    }

    public IMarkerException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public IMarkerException(Throwable throwable) {
        super(throwable);
    }
}
