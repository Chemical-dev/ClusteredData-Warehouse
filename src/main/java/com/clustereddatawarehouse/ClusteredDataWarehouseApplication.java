package com.clustereddatawarehouse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages="com.clustereddatawarehouse")
public class ClusteredDataWarehouseApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClusteredDataWarehouseApplication.class, args);
    }
}
