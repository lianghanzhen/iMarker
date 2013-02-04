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

    private final Class<T> mClazz;
    private final ParseGetCallbackListener<T> mPparseGetCallbackListener;

    public ParseGetCallback(Class<T> clazz) {
        mClazz = clazz;
        mPparseGetCallbackListener = new SimpleParseGetCallbackListener<T>();
    }

    public ParseGetCallback(Class<T> clazz, ParseGetCallbackListener<T> parseGetCallbackListener) {
        mClazz = clazz;
        if (parseGetCallbackListener == null) {
            mPparseGetCallbackListener = new SimpleParseGetCallbackListener<T>();
        } else {
            mPparseGetCallbackListener = parseGetCallbackListener;
        }
    }

    @Override
    public void done(ParseObject parseObject, ParseException e) {
        if (e == null) {
            try {
                mPparseGetCallbackListener.onParseGetCallbackSuccess(ParseProcessor.getInstance().fromParseObject(mClazz, parseObject));
            } catch (ParseProcessException exception) {
                String failMessage = String.format("Cannot process ParseObject: %s", parseObject.getClassName());
                L.e(failMessage, exception);
                mPparseGetCallbackListener.onParseCallbackFail(failMessage, exception);
            }
        } else {
            String failMessage = String.format("Cannot get ParseObject: %s", parseObject.getClassName());
            L.e(failMessage, e);
            mPparseGetCallbackListener.onParseCallbackFail(failMessage, new IMarkerException(failMessage, e));
        }
    }

}
