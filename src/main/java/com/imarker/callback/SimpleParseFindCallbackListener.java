package com.imarker.callback;

import com.imarker.exception.IMarkerException;

import java.util.List;

public class SimpleParseFindCallbackListener<T> implements ParseFindCallbackListener<T> {

    @Override
    public void onParseFindCallbackSuccess(List<T> objects) {}

    @Override
    public void onParseCallbackFail(String failMessage, IMarkerException exception) {}

}
