package com.example.trello.Dto.Validator;

import java.lang.annotation.Documented;
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
