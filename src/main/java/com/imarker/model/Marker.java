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

    private @ParseColumn String objectId;
    private @ParseColumn String username;
    private @ParseColumn String email;
    private @ParseColumn boolean emailVerified;
    private @ParseColumn Date createdAt;
    private @ParseColumn int gender = 0;

    public Marker() {}

    public Marker(String username, String email, boolean emailVerified, int gender) {
        this.email = email;
        this.emailVerified = emailVerified;
        this.gender = gender;
        this.username = username;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isEmailVerified() {
        return emailVerified;
    }

    public void setEmailVerified(boolean emailVerified) {
        this.emailVerified = emailVerified;
    }

    public String getObjectId() {
        return objectId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public static enum Gender {
        MALE(0, IMarkerApplication.getInstance().getString(R.string.male)),
        FEMALE(MALE.gender + 1, IMarkerApplication.getInstance().getString(R.string.female));

        private int gender;
        private String representation;

        Gender(int gender, String representation) {
            this.gender = gender;
            this.representation = representation;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Marker marker = (Marker) o;

        if (objectId != null ? !objectId.equals(marker.objectId) : marker.objectId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return objectId != null ? objectId.hashCode() : 0;
    }
}
