package com.example.trello.Dto.Validator;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = PhoneNumberValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface EnumRole {
    Class<? extends Enum<?>> enumClass();
    String message() default "must be any of enum {enumClass}";
    
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
