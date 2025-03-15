package com.example.trello.Dto.Validator;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;


// ConstraintValidator<EnumRole, String> thì tham số thứ 2 là kiểu dữ liệu của field cần validate
public class EnumRoleValidator implements ConstraintValidator<EnumRole, String> {
    List<String> roles;
    // long years = ChronoUnit.YEARS.between(15,LocalDate.now());
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
