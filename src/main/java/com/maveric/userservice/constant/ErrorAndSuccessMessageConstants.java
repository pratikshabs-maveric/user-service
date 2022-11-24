package com.maveric.userservice.constant;

public final class ErrorAndSuccessMessageConstants {

    private ErrorAndSuccessMessageConstants(){

    }
    public static final String MISSING_INPUT = "Input is missing or not valid";

    public static final String REQUEST_KEY_ERROR = "your request key is not proper";

    public static final String GENDER_ERROR = "Gender String should be MALE or FEMALE";

    public static final String USER_ID_MISMATCH = "User id mismatch or not authorized user";
    public static final String SUCCESS_DELETE_USER = "User Deleted Successfully";
    public static final String USER_FOUND = "User found ";
    public static final String USER_MISMATCH = "User id mismatch";
    public static final String ALL_USER = "Fetched all users";
    public static final String USER_FOUND_BY_EMAIL = "User found by Email ";
    public static final String USER_DELETE = "User deleted";
    public static final String USER_UPDATE = "User updated ";
    public static final String USER_CREATE = "User created ";
    public static final String LASTNAME_BLANK_ERROR = "lastname shouldn't be empty";
    public static final String FIRST_BLANK_ERROR = "firstname shouldn't be empty";
    public static final String EMAIL_BLANK_ERROR = "email should not be empty";
    public static final String EMAIL_REGEX = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}";
    public static final String PHONE_SIZE_ERROR = "phoneNumber should be 10 digit";
    public static final String PHONE_BLANK_ERROR = "phoneNumber shouldn't be empty";


}
