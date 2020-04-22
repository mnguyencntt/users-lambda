package com.anz.platform.enumeration;

import java.util.Arrays;
import java.util.List;

public enum UserFunctionType {
  //
  CREATE("", "Send new user"),
  //
  UPDATE("", "Update user"),
  //
  FIND("", "Find user by Id"),
  //
  FINDALL("", "Find all users"),
  //
  DELETE("", "Delete user by Id");

  private String value;
  private String desc;

  UserFunctionType(String value, String desc) {
    this.value = value;
    this.desc = desc;
  }

  public static List<UserFunctionType> getAll() {
    return Arrays.asList(UserFunctionType.values());
  }

  public String getValue() {
    return value;
  }

  public String getDesc() {
    return desc;
  }
}
