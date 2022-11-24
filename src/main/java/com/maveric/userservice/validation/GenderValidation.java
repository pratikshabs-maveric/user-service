package com.maveric.userservice.validation;

import com.maveric.userservice.constant.Gender;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;

@Documented
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = { GenderValidatorImplementation.class })
public @interface GenderValidation {
    Gender[] anyOfTheseGender();
    String message() default "Gender should either {anyOfTheseGender}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
