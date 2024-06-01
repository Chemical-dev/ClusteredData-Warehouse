package com.clustereddatawarehouse.dto.request;

import com.clustereddatawarehouse.dto.response.ValidationResponse;
import lombok.Data;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class AddDealDto {
    @NotNull(message = "Deal Unique Id is required")
    private String dealUniqueId;

    @NotNull(message = "From Currency ISO Code is required")
    private String fromCurrencyIsoCode;

    @NotNull(message = "To Currency ISO Code is required")
    private String toCurrencyIsoCode;

    @NotNull(message = "Deal timestamp is required")
    private LocalDateTime dealTimestamp;

    @NotNull(message = "Deal amount is required")
    private BigDecimal dealAmount;

    public ValidationResponse isValid() {
        if (fromCurrencyIsoCode != null && toCurrencyIsoCode != null && dealTimestamp != null && dealAmount != null && dealUniqueId != null) {
            if (fromCurrencyIsoCode.equals(toCurrencyIsoCode)) {
                return new ValidationResponse(false, "From and To currencies should not be the same");
            } else if (fromCurrencyIsoCode.length() != 3 || toCurrencyIsoCode.length() != 3) {
                return new ValidationResponse(false, "FromCurrency and ToCurrency should be 3 letters length.");
            } else if (dealAmount.compareTo(BigDecimal.ZERO) <= 0) {
                return new ValidationResponse(false, "Deal amount should be a positive value.");
            }
        } else {
            return new ValidationResponse(false, "All required fields must be provided.");
        }
        return new ValidationResponse(true, "Valid");
    }

}
