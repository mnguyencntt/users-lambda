package com.anz.platform.util;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.stream.Collectors;

public final class ObjectUtils {
  ObjectUtils() {
    throw new UnsupportedOperationException();
  }

  public static boolean isNotEmpty(final Object obj) {
    return !isEmpty(obj);
  }

  @SuppressWarnings("rawtypes")
  public static boolean isEmpty(final Object obj) {
    if (obj == null) {
      return true;
    }

    if (obj instanceof Optional) {
      return !((Optional) obj).isPresent();
    }
    if (obj instanceof CharSequence) {
      return ((CharSequence) obj).length() == 0;
    }
    if (obj.getClass().isArray()) {
      return Array.getLength(obj) == 0;
    }
    if (obj instanceof Collection) {
      return ((Collection) obj).isEmpty();
    }
    if (obj instanceof Map) {
      return ((Map) obj).isEmpty();
    }

    return false;
  }

  public static void isTrue(final boolean expression, final String message) {
    if (!expression) {
      throw new IllegalArgumentException(message);
    }
  }

  public static void notEmpty(final Object object, final String message) {
    if (isEmpty(object)) {
      throw new IllegalArgumentException(message);
    }
  }

  public static <T, U> List<U> convertList(final List<T> from, final Function<T, U> func) {
    return from.stream().map(func).collect(Collectors.toList());
  }

  public static <T, U> U[] convertArray(final T[] from, final Function<T, U> func, final IntFunction<U[]> generator) {
    return Arrays.stream(from).map(func).toArray(generator);
  }

  public static String capitalizeFirstLetter(final String str) {
    if (ObjectUtils.isEmpty(str)) {
      return Constants.EMPTY;
    }
    return str.substring(0, 1).toUpperCase() + str.substring(1);
  }

  public static Boolean isArrContain(final String input, final String... strs) {
    if (ObjectUtils.isEmpty(input) || ObjectUtils.isEmpty(strs)) {
      return Boolean.FALSE;
    }
    return Arrays.stream(strs).anyMatch(p -> p.equalsIgnoreCase(input));
  }

}
