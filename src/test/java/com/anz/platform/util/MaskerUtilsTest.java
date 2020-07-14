package com.anz.platform.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MaskerUtilsTest {
  @Test
  public void testCallPrivateConstructor()  {
    Assertions.assertThrows(InvocationTargetException.class, () -> {
      Constructor<MaskerUtils> c = MaskerUtils.class.getDeclaredConstructor();
      c.setAccessible(true);
      c.newInstance();
    });
  }

  @Test
  public void testMask()  {
    String input1 = "123456789";
    int requiredMaskBits = 4;

    assertEquals("1234", input1.substring(0, requiredMaskBits));
    assertEquals("56789", input1.substring(requiredMaskBits));
    assertEquals("*********", input1.replaceAll(".", "*"));
  }

  @Test
  public void testIgnoreLast4()  {
    String input0 = "123";
    String input1 = "123456789";

    assertEquals("", MaskerUtils.ignoreLast4(""));
    assertEquals("***", MaskerUtils.ignoreLast4(input0));
    assertEquals("*****6789", MaskerUtils.ignoreLast4(input1));
  }

  @Test
  public void testMaskPrefix()  {
    String input0 = "123";
    String input1 = "123456789";

    assertEquals("", MaskerUtils.maskPrefix("", 4));
    assertEquals("***", MaskerUtils.maskPrefix(input0, 4));
    assertEquals("*****6789", MaskerUtils.maskPrefix(input1, 4));
  }

  @Test
  public void testIgnoreFirst3()  {
    String input0 = "12";
    String input1 = "123456789";

    assertEquals("", MaskerUtils.ignoreFirst3(""));
    assertEquals("**", MaskerUtils.ignoreFirst3(input0));
    assertEquals("123******", MaskerUtils.ignoreFirst3(input1));
  }

  @Test
  public void testMaskSuffix()  {
    String input0 = "12";
    String input1 = "123456789";

    assertEquals("", MaskerUtils.maskSuffix("", 3));
    assertEquals("**", MaskerUtils.maskSuffix(input0, 3));
    assertEquals("123******", MaskerUtils.maskSuffix(input1, 3));
  }
}
