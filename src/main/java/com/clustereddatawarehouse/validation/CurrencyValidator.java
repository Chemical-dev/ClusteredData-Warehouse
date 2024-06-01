package com.clustereddatawarehouse.validation;

import com.clustereddatawarehouse.annotations.ValidCurrency;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.Currency;
import java.util.Set;
import java.util.stream.Collectors;

public class CurrencyValidator implements ConstraintValidator<ValidCurrency, String> {
    private Set<String> currencyCodes;

    @Override
    public void initialize(ValidCurrency constraintAnnotation) {
        currencyCodes = Currency.getAvailableCurrencies()
                .stream()
                .map(Currency::getCurrencyCode)
                .collect(Collectors.toSet());
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null && currencyCodes.contains(value);
    }
}
