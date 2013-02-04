package com.imarker.model;

import com.imarker.parse.ParseClass;
import com.imarker.parse.ParseColumn;

@ParseClass
public class Image {

    private @ParseColumn(columnName = "objectId") String mObjectId;
    private @ParseColumn(columnName = "title") String mTitle;
    private @ParseColumn(columnName = "url") String mUrl;
    private @ParseColumn(columnName = "contentType") String mContentType;
    private @ParseColumn(columnName = "size") long mSize;
    private @ParseColumn(columnName = "width") int mWidth;
    private @ParseColumn(columnName = "height") int mHeight;
    private @ParseColumn(columnName = "markerName") String mMarkerName;
    private @ParseColumn(columnName = "marker", columnType = ParseColumn.ColumnType.RELATION, fetchIfNeed = true) Marker mMarker;

    public Image() {}

    public Image(String title, String url, String contentType, long size, int width, int height, String markerName, Marker marker) {
        mTitle = title;
        mUrl = url;
        mContentType = contentType;
        mSize = size;
        mWidth = width;
        mHeight = height;
        mMarkerName = markerName;
        mMarker = marker;
    }

    public String getContentType() {
        return mContentType;
    }

    public void setContentType(String contentType) {
        mContentType = contentType;
    }

    public int getHeight() {
        return mHeight;
    }

    public void setHeight(int height) {
        mHeight = height;
    }

    public Marker getMarker() {
        return mMarker;
    }

    public void setMarker(Marker marker) {
        mMarker = marker;
    }

    public String getMarkerName() {
        return mMarkerName;
    }

    public void setMarkerName(String markerName) {
        mMarkerName = markerName;
    }

    public String getObjectId() {
        return mObjectId;
    }

    public long getSize() {
        return mSize;
    }

    public void setSize(long size) {
        mSize = size;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        mUrl = url;
    }

    public int getWidth() {
        return mWidth;
    }

    public void setWidth(int width) {
        mWidth = width;
    }

}
