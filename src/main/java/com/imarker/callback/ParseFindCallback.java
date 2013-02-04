package com.imarker.callback;

import com.imarker.exception.IMarkerException;
import com.imarker.exception.ParseProcessException;
import com.imarker.parse.ParseProcessor;
import com.imarker.utils.L;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;

import java.util.ArrayList;
import java.util.List;

/**
 * ParseObject FindCallback, can deal with {@link ParseCallbackListener} after FindCallback return.
 */
public class ParseFindCallback<T> extends FindCallback {

    private final Class<T> mClazz;
    private final ParseFindCallbackListener<T> mParseFindCallbackListener;

    public ParseFindCallback(Class<T> clazz) {
        mClazz = clazz;
        mParseFindCallbackListener = new SimpleParseFindCallbackListener<T>();
    }

    public ParseFindCallback(Class<T> clazz, ParseFindCallbackListener<T> parseFindCallbackListener) {
        mClazz = clazz;
        if (parseFindCallbackListener == null) {
            mParseFindCallbackListener = new SimpleParseFindCallbackListener<T>();
        } else {
            mParseFindCallbackListener = parseFindCallbackListener;
        }
    }

    @Override
    public void done(List<ParseObject> parseObjects, ParseException e) {
        if (e == null) {
            List<T> actualObjects = new ArrayList<T>(parseObjects.size());
            try {
                for (ParseObject parseObject : parseObjects) {
                    actualObjects.add(ParseProcessor.getInstance().fromParseObject(mClazz, parseObject));
                }
                mParseFindCallbackListener.onParseFindCallbackSuccess(actualObjects);
            } catch (ParseProcessException exception) {
                String failMessage = String.format("Cannot process ParseObject: %s", mClazz.getSimpleName());
                L.e(failMessage, exception);
                mParseFindCallbackListener.onParseCallbackFail(failMessage, exception);
            }
        } else {
            String failMessage = String.format("Cannot find ParseObjects: %s", mClazz.getSimpleName());
            L.e(failMessage, e);
            mParseFindCallbackListener.onParseCallbackFail(failMessage, new IMarkerException(failMessage, e));
        }
    }

}
