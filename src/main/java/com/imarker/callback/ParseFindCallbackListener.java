package com.imarker.callback;

import java.util.List;

public interface ParseFindCallbackListener<T> extends ParseCallbackListener {

    void onParseFindCallbackSuccess(List<T> objects);

}
