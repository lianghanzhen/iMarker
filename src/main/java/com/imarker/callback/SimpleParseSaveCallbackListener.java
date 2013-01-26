package com.imarker.callback;

import com.imarker.exception.IMarkerException;

public class SimpleParseSaveCallbackListener implements ParseSaveCallbackListener {

    @Override
    public void onParseSaveCallbackSuccess() {}

    @Override
    public void onParseCallbackFail(String failMessage, IMarkerException exception) {}

}
