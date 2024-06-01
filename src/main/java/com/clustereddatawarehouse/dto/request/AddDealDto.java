package com.clustereddatawarehouse.dto.request;

import com.clustereddatawarehouse.annotations.PositiveAmount;
import com.clustereddatawarehouse.annotations.ValidCurrency;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class AddDealDto {
    @NotNull(message = "Deal Unique Id is required")
    private String dealUniqueId;

    @NotNull(message = "From Currency ISO Code is required")
    @ValidCurrency
    private String fromCurrencyIsoCode;

    @NotNull(message = "To Currency ISO Code is required")
    @ValidCurrency
    private String toCurrencyIsoCode;

    @NotNull(message = "Deal timestamp is required")
    private LocalDateTime dealTimestamp;

    @NotNull(message = "Deal amount is required")
    @PositiveAmount
    private BigDecimal dealAmount;

}
