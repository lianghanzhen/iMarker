package com.imarker.model;

import com.imarker.IMarkerApplication;
import com.imarker.R;
import com.imarker.parse.ParseColumn;
import com.imarker.parse.ParseUser;

import java.util.Date;

/**
 * image marker
 */
@ParseUser
public class Marker {

    private @ParseColumn(columnName = "objectId") String mObjectId;
    private @ParseColumn(columnName = "username") String mUsername;
    private @ParseColumn(columnName = "email") String mEmail;
    private @ParseColumn(columnName = "emailVerified") boolean mEmailVerified;
    private @ParseColumn(columnName = "createdAt") Date mCreatedAt;
    private @ParseColumn(columnName = "gender") int mGender = 0;

    public Marker() {}

    public Marker(String username, String email, boolean emailVerified, int gender) {
        mEmail = email;
        mEmailVerified = emailVerified;
        mGender = gender;
        mUsername = username;
    }

    public Date getCreatedAt() {
        return mCreatedAt;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail = email;
    }

    public boolean isEmailVerified() {
        return mEmailVerified;
    }

    public void setEmailVerified(boolean emailVerified) {
        mEmailVerified = emailVerified;
    }

    public String getObjectId() {
        return mObjectId;
    }

    public String getUsername() {
        return mUsername;
    }

    public void setUsername(String username) {
        mUsername = username;
    }

    public int getGender() {
        return mGender;
    }

    public void setGender(int gender) {
        mGender = gender;
    }

    public static enum Gender {
        MALE(0, IMarkerApplication.getInstance().getString(R.string.male)),
        FEMALE(MALE.gender + 1, IMarkerApplication.getInstance().getString(R.string.female));

        private int gender;
        private String representation;

        Gender(int gender, String representation) {
            gender = gender;
            representation = representation;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Marker marker = (Marker) o;

        if (mObjectId != null ? !mObjectId.equals(marker.mObjectId) : marker.mObjectId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return mObjectId != null ? mObjectId.hashCode() : 0;
    }
}
