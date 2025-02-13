package com.example.trello.Dto.Validator;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EnumRoleValidator implements ConstraintValidator<EnumRole, String> {
    List<String> roles;

    @Override
    public void initialize(EnumRole rolesEnum) {
        roles = Stream.of(rolesEnum.enumClass().getEnumConstants()).map(Enum::name).collect(Collectors.toList());
    }

    @Override
    public boolean isValid(String role, ConstraintValidatorContext context) {
        if (role == null)
            return false;
        return roles.contains(role.toString());
    }
}
