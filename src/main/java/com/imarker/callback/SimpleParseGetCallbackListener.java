package com.imarker.callback;

import com.imarker.exception.IMarkerException;

public class SimpleParseGetCallbackListener<T> implements ParseGetCallbackListener<T> {

    @Override
    public void onParseGetCallbackSuccess(T object) {}

    @Override
    public void onParseCallbackFail(String failMessage, IMarkerException exception) {}

}
