package com.anz.platform.exception.base;

public class KnownException extends Exception {

  private static final long serialVersionUID = 1L;

  private String key;

  public KnownException(final Throwable cause) {
    super(cause);
  }

  public KnownException(final String errorMessage) {
    super(errorMessage);
  }

  public KnownException(final String errorMessage, final Throwable cause) {
    super(errorMessage, cause);
  }

  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }
}
