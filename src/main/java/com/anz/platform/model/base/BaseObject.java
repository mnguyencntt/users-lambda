package com.anz.platform.model.base;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import com.anz.platform.util.Constants;
import com.anz.platform.util.ObjectUtils;

public abstract class BaseObject {
  private Field[] findDeclaredFields() {
    Field[] declaredFields = this.getClass().getDeclaredFields();
    for (int i = 0; i < declaredFields.length; i++) {
      declaredFields[i].setAccessible(true);
    }
    return declaredFields;
  }

  private String[] findFields() {
    Field[] declaredFields = findDeclaredFields();
    final String[] fields = new String[declaredFields.length];
    for (int i = 0; i < declaredFields.length; i++) {
      fields[i] = declaredFields[i].getName();
    }
    return fields;
  }

  public String findFieldsJoining() {
    return Arrays.stream(findFields()).collect(Collectors.joining(Constants.COMMA));
  }

  public String findMarksJoining() {
    return Arrays.stream(findFields()).map(p -> Constants.QUESTION).collect(Collectors.joining(Constants.COMMA));
  }

  public String findFieldValuesJoining(final String... ignoreFields) {
    return findFieldValues(ignoreFields).stream().collect(Collectors.joining(Constants.COMMA));
  }

  public String[] findValues() {
    try {
      final Field[] declaredFields = findDeclaredFields();
      final String[] output = new String[declaredFields.length];
      for (int i = 0; i < declaredFields.length; i++) {
        final Field field = findDeclaredFields()[i];
        final Object declaredValue = field.get(this);
        if (declaredValue != null) {
          output[i] = String.valueOf(declaredValue);
        } else {
          output[i] = Constants.EMPTY; // Can be NULL
        }
      }
      return output;
    } catch (IllegalArgumentException | IllegalAccessException e) {
      return new String[0];
    }
  }

  public List<String> findFieldValues(final String... ignoreFields) {
    try {
      final Field[] declaredFields = findDeclaredFields();
      final LinkedList<String> output = new LinkedList<>();
      for (int i = 0; i < declaredFields.length; i++) {
        final Field field = findDeclaredFields()[i];
        final Object declaredValue = field.get(this);
        if (declaredValue == null || ObjectUtils.isArrContain(field.getName(), ignoreFields)) {
          continue;
        }
        output.add(field.getName() + Constants.EQUAL + String.valueOf(declaredValue));
      }
      return output;
    } catch (IllegalArgumentException | IllegalAccessException e) {
      return Collections.emptyList();
    }
  }
}
