package com.imarker.callback;

import com.imarker.exception.IMarkerException;
import com.imarker.exception.ParseProcessException;
import com.imarker.parse.ParseProcessor;
import com.imarker.utils.L;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;

/**
 * ParseObject GetCallback, can deal with {@link ParseGetCallbackListener} after GetCallback return.
 * @param <T>
 */
public class ParseGetCallback<T> extends GetCallback {

    private final Class<T> clazz;
    private final ParseGetCallbackListener<T> parseGetCallbackListener;

    public ParseGetCallback(Class<T> clazz) {
        this.clazz = clazz;
        parseGetCallbackListener = new SimpleParseGetCallbackListener<T>();
    }

    public ParseGetCallback(Class<T> clazz, ParseGetCallbackListener<T> parseGetCallbackListener) {
        this.clazz = clazz;
        if (parseGetCallbackListener == null) {
            this.parseGetCallbackListener = new SimpleParseGetCallbackListener<T>();
        } else {
            this.parseGetCallbackListener = parseGetCallbackListener;
        }
    }

    @Override
    public void done(ParseObject parseObject, ParseException e) {
        if (e == null) {
            try {
                parseGetCallbackListener.onParseGetCallbackSuccess(ParseProcessor.getInstance().fromParseObject(clazz, parseObject));
            } catch (ParseProcessException exception) {
                String failMessage = String.format("Cannot process ParseObject: %s", parseObject.getClassName());
                L.e(failMessage, exception);
                parseGetCallbackListener.onParseCallbackFail(failMessage, exception);
            }
        } else {
            String failMessage = String.format("Cannot get ParseObject: %s", parseObject.getClassName());
            L.e(failMessage, e);
            parseGetCallbackListener.onParseCallbackFail(failMessage, new IMarkerException(failMessage, e));
        }
    }

}
