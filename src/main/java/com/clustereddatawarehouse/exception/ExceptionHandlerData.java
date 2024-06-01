package com.clustereddatawarehouse.exception;

import com.clustereddatawarehouse.enums.ErrorType;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Slf4j
public class ExceptionHandlerData {

  private String code;
  private String message;
  private ErrorType error;
  private String timestamp = LocalDateTime.now().toString().replace("T", " ");

  public static String getInstance() {
    String response = "";

    try {
      ObjectMapper mapper = new ObjectMapper();
      response =
          mapper.writeValueAsString(
              ExceptionHandlerData.builder()
                  .code("S7")
                  .message("A generic error occurred")
                  .error(ErrorType.GENERIC_ERROR)
                  .build());
    } catch (Exception exception) {
      log.error("Error ", exception);
    }

    return response;
  }
}
