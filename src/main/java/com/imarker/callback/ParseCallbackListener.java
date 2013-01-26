package com.imarker.callback;

import com.imarker.exception.IMarkerException;

/**
 * use to deal with ParseObject callback operation
 */
public interface ParseCallbackListener {

    void onParseCallbackFail(String failMessage, IMarkerException exception);

}
