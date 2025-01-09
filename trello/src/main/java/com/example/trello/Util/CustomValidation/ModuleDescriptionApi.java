package com.example.trello.Util.CustomValidation;

import java.lang.annotation.Documented;

import org.springframework.http.HttpMethod;

// @Constraint(validatedBy = StrongPasswordValidator.class)
// @Target({ ElementType.METHOD, ElementType.FIELD })
// @Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ModuleDescriptionApi {
    String name();

    boolean matadataAdmin();

    String description();

    String type();

    String module();

    String path();
    String method();
}
