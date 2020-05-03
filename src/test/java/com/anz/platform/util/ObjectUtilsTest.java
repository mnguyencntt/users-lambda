package com.anz.platform.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.Arrays;
import java.util.Collections;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ObjectUtilsTest {
  @Test
  public void testConstructor() {
    Assertions.assertThrows(UnsupportedOperationException.class, () -> {
      new ObjectUtils();
    });
  }

  @Test
  public void testIsNotEmpty() {
    assertFalse(ObjectUtils.isNotEmpty(null));
    assertFalse(ObjectUtils.isNotEmpty(""));
    assertTrue(ObjectUtils.isNotEmpty("Test"));
  }

  @Test
  public void testIsEmpty() {
    assertTrue(ObjectUtils.isEmpty(null));
    assertTrue(ObjectUtils.isEmpty(""));
    assertTrue(ObjectUtils.isEmpty(new String[0]));
    assertTrue(ObjectUtils.isEmpty(Collections.emptyList()));
    assertTrue(ObjectUtils.isEmpty(Collections.emptySet()));
    assertTrue(ObjectUtils.isEmpty(Collections.emptyMap()));
    assertFalse(ObjectUtils.isEmpty("Test"));
    assertFalse(ObjectUtils.isEmpty(Arrays.asList("Test")));
  }

  @Test
  public void testIsTrue() {
    ObjectUtils.isTrue(true, "");
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      ObjectUtils.isTrue(false, "");
    });
  }

  @Test
  public void testNotEmpty() {
    ObjectUtils.notEmpty("Test", "");
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      ObjectUtils.notEmpty("", "");
    });
  }

  @Test
  public void testCapitalizeFirstLetter() {
    assertEquals("", ObjectUtils.capitalizeFirstLetter(null));
    assertEquals("", ObjectUtils.capitalizeFirstLetter(""));
    assertEquals("Test", ObjectUtils.capitalizeFirstLetter("test"));
    assertEquals("Test", ObjectUtils.capitalizeFirstLetter("Test"));
  }

  @Test
  public void testIsArrContain() {
    assertEquals(false, ObjectUtils.isArrContain(null));
    assertEquals(false, ObjectUtils.isArrContain(""));
    assertEquals(false, ObjectUtils.isArrContain("", new String[0]));
    assertEquals(false, ObjectUtils.isArrContain("Test", "Test1", "Test2"));

    assertEquals(true, ObjectUtils.isArrContain("Test1", "Test1", "Test2"));
  }
}
