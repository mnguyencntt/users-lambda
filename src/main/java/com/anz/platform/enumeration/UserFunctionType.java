package com.anz.platform.enumeration;

import java.util.Arrays;
import java.util.List;

public enum UserFunctionType {
  //
  CREATE("", "Send new user"),
  //
  UPDATE("", "Update user"),
  //
  AUTHENTICATE("", "Authenticate by Username/Password"),
  //
  FINDID("", "Find user by Id"),
  //
  FINDUSERNAME("", "Find user by Username"),
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
