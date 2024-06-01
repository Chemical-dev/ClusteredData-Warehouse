package com.clustereddatawarehouse.service;


import com.clustereddatawarehouse.dto.request.AddDealDto;
import com.clustereddatawarehouse.dto.response.AppResponse;
import org.springframework.http.ResponseEntity;


public interface DealService {
 ResponseEntity<?> addDeal(AddDealDto dto);
}
