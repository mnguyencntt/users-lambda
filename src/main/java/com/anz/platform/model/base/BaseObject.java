package com.anz.platform.model.base;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.stream.Collectors;
import com.anz.platform.util.Constants;

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

  public String[] findValues() throws IllegalArgumentException, IllegalAccessException {
    final Field[] declaredFields = findDeclaredFields();
    final String[] output = new String[declaredFields.length];
    for (int i = 0; i < declaredFields.length; i++) {
      Object declaredValue = findDeclaredFields()[i].get(this);
      if (declaredValue != null) {
        output[i] = String.valueOf(declaredValue);
      } else {
        output[i] = Constants.EMPTY; // Can be NULL
      }
    }
    return output;
  }
}
