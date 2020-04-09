package com.anz.platform.exception;

import com.anz.platform.exception.base.UnknownException;

public class InvalidRequestException extends UnknownException {

  private static final long serialVersionUID = 1L;

  public InvalidRequestException(final String errorMessage) {
    super(errorMessage);
  }

  public InvalidRequestException(final String errorMessage, final String key) {
    super(errorMessage);
    this.setKey(key);
  }
}
