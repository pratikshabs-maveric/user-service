package com.maveric.userservice.model;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.maveric.userservice.constant.ErrorAndSuccessMessageConstants;
import com.maveric.userservice.constant.Gender;
import com.maveric.userservice.validation.DateValidation;
import com.maveric.userservice.validation.GenderValidation;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;
@Getter
@Setter
@Entity
@Table(name = "user_details")
public class User {

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;

    @NotBlank(message = ErrorAndSuccessMessageConstants.FIRST_BLANK_ERROR)
    private String firstName;

    private String middleName;

    @NotBlank(message = ErrorAndSuccessMessageConstants.LASTNAME_BLANK_ERROR)
    private String lastName;

    @NotBlank(message = ErrorAndSuccessMessageConstants.EMAIL_BLANK_ERROR)
    @Email(regexp = ErrorAndSuccessMessageConstants.EMAIL_REGEX)
    private String email;

    @Size(min = 10,max = 10 ,message = ErrorAndSuccessMessageConstants.PHONE_SIZE_ERROR)
    @NotBlank(message = ErrorAndSuccessMessageConstants.PHONE_BLANK_ERROR)
    private String phoneNumber;

    @NotBlank
    private String address;

    @NotBlank
    @DateValidation
    private String dateOfBirth;

    @Enumerated(EnumType.STRING)
    @GenderValidation(anyOfTheseGender = {Gender.FEMALE,Gender.MALE})
    private Gender gender;

    private String role;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Column(updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt = new Date();

    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt = new Date();
}

