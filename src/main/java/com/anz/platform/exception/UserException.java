package com.anz.platform.exception;

import com.anz.platform.exception.base.UnknownException;

public class UserException extends UnknownException {

  private static final long serialVersionUID = 1L;

  public UserException(final String errorMessage) {
    super(errorMessage);
  }

  public UserException(final String errorMessage, final String key) {
    super(errorMessage);
    this.setKey(key);
  }
}
