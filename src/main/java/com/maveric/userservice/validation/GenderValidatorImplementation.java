package com.maveric.userservice.validation;

import com.maveric.userservice.constant.Gender;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

public class GenderValidatorImplementation implements ConstraintValidator <GenderValidation,Gender>{

    private Gender[] allGenderValue;

    @Override
    public void initialize(GenderValidation constraintAnnotation) {
        this.allGenderValue = constraintAnnotation.anyOfTheseGender();
    }
    @Override
    public boolean isValid(Gender gender, ConstraintValidatorContext constraintValidatorContext) {
        return gender == null || Arrays.asList(allGenderValue).contains(gender);
    }
}
