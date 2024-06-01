package com.clustereddatawarehouse.validation;

import com.clustereddatawarehouse.annotations.PositiveAmount;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.math.BigDecimal;

public class PositiveAmountValidator implements ConstraintValidator<PositiveAmount, BigDecimal> {
    @Override
    public boolean isValid(BigDecimal dealAmount, ConstraintValidatorContext context) {
        if (dealAmount == null) {
            return true;
        }
        return dealAmount.compareTo(BigDecimal.ZERO) > 0;
    }
}

