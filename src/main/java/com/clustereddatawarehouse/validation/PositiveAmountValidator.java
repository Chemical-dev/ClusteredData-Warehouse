package com.clustereddatawarehouse.validation;

import com.clustereddatawarehouse.annotations.PositiveAmount;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.math.BigDecimal;

public class PositiveAmountValidator implements ConstraintValidator<PositiveAmount, BigDecimal> {

    @Override
    public void initialize(PositiveAmount constraintAnnotation) {
        // Any initialization logic goes here
    }

    @Override
    public boolean isValid(BigDecimal dealAmount, ConstraintValidatorContext context) {
        // Null values are considered valid as they can be handled by @NotNull separately
        if (dealAmount == null) {
            return true;
        }
        System.out.println("i am here");
        return dealAmount.compareTo(BigDecimal.ZERO) > 0;
    }
}

