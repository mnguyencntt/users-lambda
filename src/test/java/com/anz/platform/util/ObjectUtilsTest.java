package com.anz.platform.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ObjectUtilsTest {
  @Test
  public void testConstructor() {
    Assertions.assertThrows(InvocationTargetException.class, () -> {
      Constructor<ObjectUtils> c = ObjectUtils.class.getDeclaredConstructor();
      c.setAccessible(true);
      c.newInstance();
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
    assertTrue(ObjectUtils.isEmpty(Optional.empty()));
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
  public void testIsBlank() {
    assertTrue(ObjectUtils.isBlank(null));
    assertTrue(ObjectUtils.isBlank(""));
    assertTrue(ObjectUtils.isBlank("   "));
    assertFalse(ObjectUtils.isBlank("Test"));
  }

  @Test
  public void testConvertArray() throws Exception {
    Function<String, StringBuffer> func = StringBuffer::new;

    String[] tests = {"Test1", "Test2", "Test3"};
    StringBuffer[] stringBuffers = ObjectUtils.convertArray(tests, func, StringBuffer[]::new);
    assertEquals(3, stringBuffers.length);
  }

  @Test
  public void testConvertList() throws Exception {
    Function<String, StringBuffer> func = StringBuffer::new;

    List<String> names = Arrays.asList("Test1", "Test2", "Test3");
    List<StringBuffer> list = ObjectUtils.convertList(names, func);
    assertEquals(3, list.size());
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

  @Test
  public void testIsArrStartWith() {
    assertEquals(false, ObjectUtils.isArrStartWith(null));
    assertEquals(false, ObjectUtils.isArrStartWith(""));
    assertEquals(false, ObjectUtils.isArrStartWith("", new String[0]));

    assertEquals(true, ObjectUtils.isArrStartWith("Test", "Test1", "Test2"));
    assertEquals(true, ObjectUtils.isArrContain("Test1", "Test1", "Test2"));
    assertEquals(false, ObjectUtils.isArrContain("Test1", "Test0", "Test2"));
  }

  @Test
  public void testIsListContain() {
    assertEquals(false, ObjectUtils.isListContain(null, Collections.emptyList()));
    assertEquals(false, ObjectUtils.isListContain("", Collections.emptyList()));
    assertEquals(false, ObjectUtils.isListContain("", Collections.emptyList()));
    assertEquals(false, ObjectUtils.isListContain("Test", Arrays.asList("Test1", "Test2")));
    assertEquals(true, ObjectUtils.isListContain("Test1", Arrays.asList("Test1", "Test2")));
  }

  @Test
  public void testIsInputStartWith() {
    assertEquals(false, ObjectUtils.isInputStartWith(null));
    assertEquals(false, ObjectUtils.isInputStartWith(""));
    assertEquals(false, ObjectUtils.isInputStartWith("", new String[0]));

    assertEquals(true, ObjectUtils.isInputStartWith("Test1", "Te", "st"));
    assertEquals(true, ObjectUtils.isInputStartWith("Test2", "st", "Te"));
    assertEquals(false, ObjectUtils.isInputStartWith("Test1", "", ""));
    assertEquals(false, ObjectUtils.isInputStartWith("Test2", "es", "st"));
  }

  @Test
  public void testEquals() {
    assertTrue(ObjectUtils.equals("", ""));
    assertTrue(ObjectUtils.equals("", ""));
    assertTrue(ObjectUtils.equals("TesT", "TesT"));
    assertTrue(ObjectUtils.equals("TEST", "test"));

    assertTrue(ObjectUtils.equals("", "", true));
    assertTrue(ObjectUtils.equals("", "", false));
    assertTrue(ObjectUtils.equals("TesT", "TesT", true));
    assertTrue(ObjectUtils.equals("TesT", "TesT", false));
    assertTrue(ObjectUtils.equals("TEST", "test", true));

    assertFalse(ObjectUtils.equals("TEST", "test", false));
    assertFalse(ObjectUtils.equals("", "1", false));
  }

  @Test
  public void testToStringBoolean() {
    Boolean test = null;
    assertEquals("false", ObjectUtils.toStringBoolean(test));
    test = false;
    assertEquals("false", ObjectUtils.toStringBoolean(test));
    test = true;
    assertEquals("true", ObjectUtils.toStringBoolean(test));

    assertEquals("false", ObjectUtils.toStringBoolean(""));
    assertEquals("false", ObjectUtils.toStringBoolean("AAA"));
    assertEquals("false", ObjectUtils.toStringBoolean("false"));
    assertEquals("true", ObjectUtils.toStringBoolean("true"));
  }

  @Test
  public void testContains() {
    assertEquals(true, ObjectUtils.contains("Test1", "Test"));
    assertEquals(true, ObjectUtils.contains("Test12345", "Test1"));
    assertEquals(true, ObjectUtils.contains("Test1", "TEST"));
    assertEquals(true, ObjectUtils.contains("Test12345", "TEST1"));

    assertEquals(false, ObjectUtils.contains("Test", "Test1"));
    assertEquals(false, ObjectUtils.contains("Test1", "Test12345"));
  }
}
