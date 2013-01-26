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

    private final Class<T> clazz;
    private final ParseFindCallbackListener<T> parseFindCallbackListener;

    public ParseFindCallback(Class<T> clazz) {
        this.clazz = clazz;
        this.parseFindCallbackListener = new SimpleParseFindCallbackListener<T>();
    }

    public ParseFindCallback(Class<T> clazz, ParseFindCallbackListener<T> parseFindCallbackListener) {
        this.clazz = clazz;
        if (parseFindCallbackListener == null) {
            this.parseFindCallbackListener = new SimpleParseFindCallbackListener<T>();
        } else {
            this.parseFindCallbackListener = parseFindCallbackListener;
        }
    }

    @Override
    public void done(List<ParseObject> parseObjects, ParseException e) {
        if (e == null) {
            List<T> actualObjects = new ArrayList<T>(parseObjects.size());
            try {
                for (ParseObject parseObject : parseObjects) {
                    actualObjects.add(ParseProcessor.getInstance().fromParseObject(clazz, parseObject));
                }
                parseFindCallbackListener.onParseFindCallbackSuccess(actualObjects);
            } catch (ParseProcessException exception) {
                String failMessage = String.format("Cannot process ParseObject: %s", clazz.getSimpleName());
                L.e(failMessage, exception);
                parseFindCallbackListener.onParseCallbackFail(failMessage, exception);
            }
        } else {
            String failMessage = String.format("Cannot find ParseObjects: %s", clazz.getSimpleName());
            L.e(failMessage, e);
            parseFindCallbackListener.onParseCallbackFail(failMessage, new IMarkerException(failMessage, e));
        }
    }

}
