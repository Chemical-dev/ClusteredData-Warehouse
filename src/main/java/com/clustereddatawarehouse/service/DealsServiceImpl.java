package com.clustereddatawarehouse.service;

import com.clustereddatawarehouse.dto.request.AddDealDto;
import com.clustereddatawarehouse.dto.response.AppResponse;
import com.clustereddatawarehouse.dto.response.ErrorResponse;
import com.clustereddatawarehouse.dto.response.ValidationResponse;
import com.clustereddatawarehouse.model.Deal;
import com.clustereddatawarehouse.model.DealsRepository;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DealsServiceImpl implements DealService{
    private final DealsRepository dealsRepository;

    @Override
    public ResponseEntity<?> addDeal(AddDealDto dto) {
        try {
//            ValidationResponse validationResult = dto.isValid();
//
//            if (!validationResult.getStatus()) {
//                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(validationResult.getMessage(), HttpStatus.BAD_REQUEST.toString()));
//            }

            Boolean dealExists = dealsRepository.existsByDealUniqueIdOrDealAmountOrFromCurrencyIsoCodeOrToCurrencyIsoCode(dto.getDealUniqueId(), dto.getDealAmount(), dto.getFromCurrencyIsoCode(), dto.getToCurrencyIsoCode());
            if (dealExists) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("Deal already exists", HttpStatus.BAD_REQUEST.toString()));
            }

            saveDeal(dto);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new AppResponse("Deal added successfully", "00", null));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse("Internal Server Error: " + exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.toString()));
        }
    }


    private void saveDeal(@NotNull AddDealDto dto){
        Deal deal = Deal.builder().dealUniqueId(dto.getDealUniqueId())
                .dealAmount(dto.getDealAmount())
                .fromCurrencyIsoCode(dto.getFromCurrencyIsoCode())
                .toCurrencyIsoCode(dto.getToCurrencyIsoCode())
                .dealTimestamp(dto.getDealTimestamp())
                .build();
        dealsRepository.save(deal);
    }
}
