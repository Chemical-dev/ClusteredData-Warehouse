package com.clustereddatawarehouse.annotations;

import com.clustereddatawarehouse.validation.PositiveAmountValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.springframework.stereotype.Component;


import java.lang.annotation.*;

@Component
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = PositiveAmountValidator.class)
public @interface PositiveAmount {
    public String message() default "Only positive numbers are allowed";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}
