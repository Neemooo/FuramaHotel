package com.example.demo2608.annotation_custom;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class CheckDayNow implements ConstraintValidator<DayMustBiggerNow, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value.isEmpty()) return false;
        return LocalDate.parse(value).isAfter(LocalDate.now());
    }

}
