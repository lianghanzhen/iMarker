package com.imarker.model;

import com.imarker.parse.ParseClass;
import com.imarker.parse.ParseColumn;

@ParseClass
public class Image {

    private @ParseColumn String objectId;
    private @ParseColumn String title;
    private @ParseColumn String url;
    private @ParseColumn String contentType;
    private @ParseColumn long size;
    private @ParseColumn int width;
    private @ParseColumn int height;
    private @ParseColumn String markerName;
    @ParseColumn(columnType = ParseColumn.ColumnType.RELATION, fetchIfNeed = true)
    private Marker marker;

    public Image() {}

    public Image(String title, String url, String contentType, long size, int width, int height, String markerName) {
        this.title = title;
        this.url = url;
        this.contentType = contentType;
        this.size = size;
        this.width = width;
        this.height = height;
        this.markerName = markerName;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Marker getMarker() {
        return marker;
    }

    public void setMarker(Marker marker) {
        this.marker = marker;
    }

    public String getMarkerName() {
        return markerName;
    }

    public void setMarkerName(String markerName) {
        this.markerName = markerName;
    }

    public String getObjectId() {
        return objectId;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
}
