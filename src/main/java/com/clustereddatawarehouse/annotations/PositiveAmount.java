package com.clustereddatawarehouse.annotations;

import com.clustereddatawarehouse.validation.PositiveAmountValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PositiveAmountValidator.class)
public @interface PositiveAmount {
    String message() default "Amount must be positive";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
