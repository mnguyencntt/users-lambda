package com.anz.platform.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApiResponse {
  private String status;
  private Object data;
  private String message;

  public static ApiResponse build(final String status, final Object data) {
    return ApiResponse.builder().status(status).data(data).build();
  }

  public static ApiResponse build(final String status, final String message) {
    return ApiResponse.builder().status(status).message(message).build();
  }

  public static ApiResponse build(final String status, final Object data, final String message) {
    return ApiResponse.builder().status(status).data(data).message(message).build();
  }
}
