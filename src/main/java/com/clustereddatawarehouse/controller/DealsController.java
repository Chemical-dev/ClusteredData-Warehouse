package com.clustereddatawarehouse.controller;

import com.clustereddatawarehouse.dto.request.AddDealDto;
import com.clustereddatawarehouse.service.DealsServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import jakarta.persistence.*;
import javax.validation.Valid;
import javax.validation.executable.ValidateOnExecution;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/deals")
@RequiredArgsConstructor
public class DealsController {
    private final DealsServiceImpl dealService;

    @PostMapping("/deals")
    public ResponseEntity<?> createDeal(@RequestBody AddDealDto deal, HttpServletRequest request) {
        String clientIp = request.getRemoteAddr();
        System.out.println("Request received from IP: " + clientIp);
        return dealService.addDeal(deal);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
