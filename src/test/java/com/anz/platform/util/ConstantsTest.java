package com.anz.platform.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ConstantsTest {
  @Test
  public void testConstructor() {
    Assertions.assertThrows(UnsupportedOperationException.class, () -> {
      new Constants();
    });
  }
}
