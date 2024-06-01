package com.clustereddatawarehouse.service;


import com.clustereddatawarehouse.dto.request.AddDealDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;


public interface DealService {
 ResponseEntity<?> addDeal(AddDealDto dto);
}
