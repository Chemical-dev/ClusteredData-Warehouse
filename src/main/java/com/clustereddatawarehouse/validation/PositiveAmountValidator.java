package com.clustereddatawarehouse.validation;

import com.clustereddatawarehouse.annotations.PositiveAmount;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.math.BigDecimal;

public class PositiveAmountValidator implements ConstraintValidator<PositiveAmount, BigDecimal> {

    @Override
    public boolean isValid(BigDecimal dealAmount, ConstraintValidatorContext context) {
        // Null values are considered valid as they can be handled by @NotNull separately
        if (dealAmount == null) {
            return true;
        }
        System.out.println("dealAmount: " + dealAmount);
        return dealAmount.compareTo(BigDecimal.ZERO) > 0;
    }
}

