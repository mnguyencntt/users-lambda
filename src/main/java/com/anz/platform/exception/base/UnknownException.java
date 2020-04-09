package com.anz.platform.exception.base;

public class UnknownException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  private String key;

  public UnknownException(final Throwable cause) {
    super(cause);
  }

  public UnknownException(final String errorMessage) {
    super(errorMessage);
  }

  public UnknownException(final String errorMessage, final Throwable cause) {
    super(errorMessage, cause);
  }

  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }
}
