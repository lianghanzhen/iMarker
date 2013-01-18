package com.imarker.model;

import com.imarker.IMarkerApplication;
import com.imarker.R;

/**
 * image marker
 */
public class Marker {

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

    private String objectId;
    private String name;
    private Gender gender;

    public Marker() {}

    public Marker(String objectId, String name, Gender gender) {
        this.objectId = objectId;
        this.name = name;
        this.gender = gender;
    }

    public String getId() {
        return objectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

}
