package com.imarker.model;

import com.imarker.IMarkerApplication;
import com.imarker.R;
import com.parse.ParseUser;

/**
 * image marker
 */
public class Marker extends ParseUser {

    private Gender gender = Gender.MALE;

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
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

}
