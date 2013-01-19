package com.imarker;

public final class Constants {

    private Constants() {}

    // [start] Parse.com service constants

    public static final String PARSE_APP_ID = "N5eirbQSi00s0yzLtadnJdiQY20PirOv0L4jzgtD";
    public static final String PARSE_CLIENT_KEY = "zaw8wYFcfOU0DLiKth7duUF4xgOOBNziwWuR5fE4";

    // [end] Parse.com service constants

    // [start] Parse.com service reserve columns and classes

    // common Parse.com class
    public static final String PARSE_RESERVE_COLUMN_OBJECT_ID = "objectId";
    public static final String PARSE_RESERVE_COLUMN_CREATED_AT = "createdAt";
    public static final String PARSE_RESERVE_COLUMN_UPDATED_AT = "updatedAt";
    public static final String PARSE_RESERVE_COLUMN_ACL = "ACL";

    // Parse.com User class
    public static final String PARSE_RESERVE_CLASS_USER = "_User";
    public static final String PARSE_USER_RESERVE_COLUMN_USERNAME = "username";
    public static final String PARSE_USER_RESERVE_COLUMN_PASSWORD = "password";
    public static final String PARSE_USER_RESERVE_COLUMN_EMAIL = "email";
    public static final String PARSE_USER_RESERVE_COLUMN_EMAIL_VERIFIED = "emailVerified";
    public static final String PARSE_USER_RESERVE_COLUMN_AUTH_DATA = "authData";

    // [end] Parse.com service reserve columns

}
