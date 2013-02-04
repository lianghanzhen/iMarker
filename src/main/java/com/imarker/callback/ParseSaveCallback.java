package com.imarker.callback;

import com.imarker.exception.IMarkerException;
import com.imarker.utils.L;
import com.parse.ParseException;
import com.parse.SaveCallback;

/**
 * ParseObject SaveCallback, can deal with {@link ParseSaveCallbackListener} after SaveCallback return.
 */
public class ParseSaveCallback extends SaveCallback {

    private final ParseSaveCallbackListener mParseSaveCallbackListener;

    public ParseSaveCallback(ParseSaveCallbackListener parseSaveCallbackListener) {
        if (parseSaveCallbackListener == null) {
            mParseSaveCallbackListener = new SimpleParseSaveCallbackListener();
        } else {
            mParseSaveCallbackListener = parseSaveCallbackListener;
        }
    }

    @Override
    public void done(ParseException e) {
        if (e == null) {
            mParseSaveCallbackListener.onParseSaveCallbackSuccess();
        } else {
            String failMessage = "Cannot save ParseObject";
            L.e(failMessage, e);
            mParseSaveCallbackListener.onParseCallbackFail(failMessage, new IMarkerException(failMessage, e));
        }
    }

}
