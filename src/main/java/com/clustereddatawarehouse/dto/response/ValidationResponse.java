package com.clustereddatawarehouse.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ValidationResponse {
    private Boolean status;
    private String message;
}
