package com.anz.platform.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ConstantsTest {
  @Test
  public void testConstructor() {
    Assertions.assertThrows(InvocationTargetException.class, () -> {
      Constructor<Constants> c = Constants.class.getDeclaredConstructor();
      c.setAccessible(true);
      c.newInstance();
    });
  }
}
