package com.maveric.userservice.model;

import com.maveric.userservice.enumeration.Gender;
import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Builder
@Table(name = "UserDetails")
public class User {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private long id;
    private String firstName;
    private String lastName;
    private String middleName;
    private String phoneNumber;
    private String email;
    private String address;
    private String dateOfBirth;
    private Gender gender;

}

