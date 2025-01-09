package com.example.trello.Util.CustomValidation;

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
