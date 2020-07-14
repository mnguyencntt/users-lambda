package com.anz.platform.exception;

public class UserException extends RuntimeException {
  private static final long serialVersionUID = 1L;

  public UserException(final String errorMessage) {
    super(errorMessage);
  }

  public UserException(final String errorMessage, final String key) {
    super(errorMessage);
  }
}
