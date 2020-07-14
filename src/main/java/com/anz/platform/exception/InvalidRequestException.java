package com.anz.platform.exception;

public class InvalidRequestException extends RuntimeException {
  private static final long serialVersionUID = 1L;

  public InvalidRequestException(final String errorMessage) {
    super(errorMessage);
  }

  public InvalidRequestException(final String errorMessage, final String key) {
    super(errorMessage);
  }
}
