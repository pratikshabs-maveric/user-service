package com.maveric.userservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.maveric.userservice.constants.DateDeSerializer;
import com.maveric.userservice.constants.PhoneNumber;
import com.maveric.userservice.enumeration.Gender;
import lombok.*;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor
@Builder
public class UserResponse {

    private long id;
    @NotEmpty(message = "Please enter firstName")
    @Size(min=2, message = "Length should be more than 2 CHARACTERS")
    private String firstName;

    @NotEmpty(message = "Please enter lastName")
    @Size(min=2, message = "Length should be more than 2 CHARACTERS")
    private String lastName;


    private String middleName;

    @PhoneNumber(message = "This phone number is not valid")
    @NotEmpty(message = "Please enter Phone Number")
    @Size(min=10, max=10, message = "Number cannot be Less than 10 DIGITS")
    private String phoneNumber;

    @NotBlank(message = "Please Enter the email")
    @Email(message = "Provide Valid Email")
    private String email;

    @NotEmpty(message = "Please enter the address")
    private String address;

    @JsonDeserialize(using = DateDeSerializer.class)
    @NotNull(message = "Date of Birth is mandatory")
//    @Past(message = "Date Should be past")
//    private String dateOfBirth;
    private LocalDateTime dateOfBirth;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Gender is mandatory 'MALE' or 'FEMALE'")
    private Gender gender;

//    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotBlank(message = "Password is mandatory")
    private String Password;
}
