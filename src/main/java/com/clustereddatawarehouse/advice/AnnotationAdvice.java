package com.clustereddatawarehouse.advice;

import com.clustereddatawarehouse.annotations.PositiveAmount;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.annotation.Order;

import java.lang.reflect.Method;

@Aspect
@Order(100)
@Configuration
@EnableAspectJAutoProxy
@RequiredArgsConstructor
public class AnnotationAdvice {
    private final Logger log = LoggerFactory.getLogger(AnnotationAdvice.class);

    @Pointcut("execution(@com.clustereddatawarehouse.annotations.PositiveAmount * *(..))")
    public void chargeOperations() {
        // configuration method
    }

    @Around("chargeOperations()")
    public Object advisePostMappingAnnotation(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        if (!this.isChargeable(proceedingJoinPoint)) {
            return proceedingJoinPoint.proceed();
        } else {
            Object object = proceedingJoinPoint.proceed();
            //log.info("activityName: {} object: {}", getChargeActivityName(proceedingJoinPoint), object);
            System.out.printf("object:     {}", object);
            return object;
        }
    }

    private boolean isChargeable(ProceedingJoinPoint proceedingJoinPoint) {
        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
        Method method = signature.getMethod();
        PositiveAmount charged = method.getAnnotation(PositiveAmount.class);
        return charged != null;
    }
}
