package com.clustereddatawarehouse.advice;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Aspect
@Component
@Slf4j
@RequiredArgsConstructor
public class LoggingAspect {
    private ThreadLocal<Instant> entryTime = new ThreadLocal<>();
    private final HttpServletRequest request;
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").withZone(ZoneId.of("UTC"));

    @Before("execution(* com.clustereddatawarehouse.controller.*.*(..))")
    public void logControllerEntry(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        String ipAddress = request.getRemoteAddr();
        String url = request.getRequestURL().toString();
        entryTime.set(Instant.now());
        String entryTimeString = formatter.format(entryTime.get());
        log.info("Start/Entering controller method '{}' at {} from IP: {} URL: {}",
                methodName, entryTimeString, ipAddress, url);
    }

    @AfterReturning("execution(* com.clustereddatawarehouse.controller..*.*(..))")
    public void logControllerExit(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        Instant exitTime = Instant.now();
        String exitTimeString = formatter.format(exitTime);
        long durationMillis = Duration.between(entryTime.get(), exitTime).toMillis();
        log.info("Exiting controller method '{}' at {} (Duration: {} ms)",
                methodName, exitTimeString, durationMillis);

        if(!methodName.equalsIgnoreCase("payout")){
            entryTime.remove();
        }

        if(methodName.equalsIgnoreCase("proxyPayout")){
            entryTime.remove();
        }
    }
}
