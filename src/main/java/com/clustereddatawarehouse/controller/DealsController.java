package com.clustereddatawarehouse.controller;

import com.clustereddatawarehouse.dto.request.AddDealDto;
import com.clustereddatawarehouse.service.DealsServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/deals")
@RequiredArgsConstructor
public class DealsController {
    private final DealsServiceImpl dealService;

    @PostMapping(path = "/deals",  produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createDeal(@Valid() @RequestBody AddDealDto deal) {
        return dealService.addDeal(deal);
    }
}
