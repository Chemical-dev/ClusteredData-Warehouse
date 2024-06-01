package com.clustereddatawarehouse.configuration;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

//
//@Configuration
//public class HttpServletConfig {
//    @Bean
//    @RequestScope
//    public HttpServletRequest httpServletRequest() {
//        return (HttpServletRequest) ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//    }
//}
