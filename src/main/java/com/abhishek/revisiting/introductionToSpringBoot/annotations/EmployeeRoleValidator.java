package com.abhishek.revisiting.introductionToSpringBoot.annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;

public class EmployeeRoleValidator implements ConstraintValidator<EmployeeRoleValidation, String> {
    @Override
    public boolean isValid(String incomingRole, ConstraintValidatorContext constraintValidatorContext) {
        List<String> roles = List.of("ADMIN", "USER");
        return roles.contains(incomingRole);
    }
}
