package com.maveric.userservice.dto;

import com.maveric.userservice.constant.Gender;
import lombok.Data;

@Data
public class UserEmailDto {

    private String id;

    private String firstName;

    private String middleName;

    private String lastName;

    private String email;

    private String phoneNumber;

    private String address;

    private String dateOfBirth;

    private Gender gender;

    private String password;
}
