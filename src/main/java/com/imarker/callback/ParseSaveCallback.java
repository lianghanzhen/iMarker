package com.imarker.callback;

import com.imarker.exception.IMarkerException;
import com.imarker.utils.L;
import com.parse.ParseException;
import com.parse.SaveCallback;

/**
 * ParseObject SaveCallback, can deal with {@link ParseSaveCallbackListener} after SaveCallback return.
 */
public class ParseSaveCallback extends SaveCallback {

    private final ParseSaveCallbackListener parseSaveCallbackListener;

    public ParseSaveCallback(ParseSaveCallbackListener parseSaveCallbackListener) {
        if (parseSaveCallbackListener == null) {
            this.parseSaveCallbackListener = new SimpleParseSaveCallbackListener();
        } else {
            this.parseSaveCallbackListener = parseSaveCallbackListener;
        }
    }

    @Override
    public void done(ParseException e) {
        if (e == null) {
            parseSaveCallbackListener.onParseSaveCallbackSuccess();
        } else {
            String failMessage = "Cannot save ParseObject";
            L.e(failMessage, e);
            parseSaveCallbackListener.onParseCallbackFail(failMessage, new IMarkerException(failMessage, e));
        }
    }

}
