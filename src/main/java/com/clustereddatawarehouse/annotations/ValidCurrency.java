package com.clustereddatawarehouse.annotations;

import com.clustereddatawarehouse.validation.CurrencyValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CurrencyValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidCurrency {
    String message() default "Invalid currency ISO code";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
