package com.maveric.userservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.maveric.userservice.constant.Gender;
import com.maveric.userservice.validation.DateValidation;
import com.maveric.userservice.validation.GenderValidation;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class UserDto {

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;

    @NotBlank(message = "firstname shouldn't be empty")
    private String firstName;

    private String middleName;

    @NotBlank(message = "lastname shouldn't be empty")
    private String lastName;

    @NotBlank(message = "email should not be empty")
    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}")
    private String email;

    @Size(min = 10,max = 10 ,message = "phoneNumber should be 10 digit")
    @NotBlank(message = "phoneNumber shouldn't be empty")
    private String phoneNumber;

    @NotBlank(message = "address shouldn't be empty")
    private String address;

    @NotBlank
    @DateValidation
    private String dateOfBirth;

    @Enumerated(EnumType.STRING)
    @GenderValidation(anyOfTheseGender = {Gender.FEMALE,Gender.MALE})
    private Gender gender;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
}
