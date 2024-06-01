package com.clustereddatawarehouse.dto.request;

import com.clustereddatawarehouse.annotations.PositiveAmount;
import com.clustereddatawarehouse.annotations.ValidCurrency;
import com.clustereddatawarehouse.dto.response.DealMethodValidationResponse;
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

    public DealMethodValidationResponse isValid() {
        if (fromCurrencyIsoCode != null && toCurrencyIsoCode != null && dealTimestamp != null && dealAmount != null && dealUniqueId != null) {
            if (fromCurrencyIsoCode.equals(toCurrencyIsoCode)) {
                return new DealMethodValidationResponse(false, "From and To currencies should not be the same");
            } else if (fromCurrencyIsoCode.length() != 3 || toCurrencyIsoCode.length() != 3) {
                return new DealMethodValidationResponse(false, "FromCurrency and ToCurrency should be 3 letters length.");
            } else if (dealAmount.compareTo(BigDecimal.ZERO) <= 0) {
                return new DealMethodValidationResponse(false, "Deal amount should be a positive value.");
            }
        } else {
            return new DealMethodValidationResponse(false, "All required fields must be provided.");
        }
        return new DealMethodValidationResponse(true, "Valid");
    }

}
