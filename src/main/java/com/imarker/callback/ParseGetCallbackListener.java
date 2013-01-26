package com.imarker.callback;

public interface ParseGetCallbackListener<T> extends ParseCallbackListener {

    void onParseGetCallbackSuccess(T object);

}
