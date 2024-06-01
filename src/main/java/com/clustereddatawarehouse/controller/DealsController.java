package com.clustereddatawarehouse.controller;

import com.clustereddatawarehouse.dto.request.AddDealDto;
import com.clustereddatawarehouse.service.DealsServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/deals")
@RequiredArgsConstructor
public class DealsController {
    private final DealsServiceImpl dealService;

    @PostMapping("/deals")
    public ResponseEntity<?> createDeal(@Valid() @RequestBody AddDealDto deal) {
        return dealService.addDeal(deal);
    }
}
