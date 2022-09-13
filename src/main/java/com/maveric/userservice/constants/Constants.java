package com.maveric.userservice.constants;

import java.time.LocalDateTime;

public class Constants {

    private Constants()
    {

    }

    public static final String USER_NOT_FOUND_CODE="404";
    public static final String EMAIL_NOT_FOUND_CODE="400";
    public static final String ENUM_INVALID="400";
    public static final String BAD_REQUEST_CODE="400";
    public static final String BAD_REQUEST_MESSAGE="Invalid inputs!";

    public static final String INVALID_INPUT_TYPE="Gender should be MALE/FEMALE";

    public static final String HttpMessageNotReadableException_MESSAGE="Format Miss Matching";
    public static LocalDateTime getCurrentDateTime() {
        return (LocalDateTime.now());
    }
}
