package com.anz.platform.enumeration;

import java.util.Arrays;
import java.util.List;

public enum UserType {
  //
  SELLER("", ""),
  //
  BUYER("", "");

  private String value;
  private String desc;

  UserType(String value, String desc) {
    this.value = value;
    this.desc = desc;
  }

  public static List<UserType> getAll() {
    return Arrays.asList(UserType.values());
  }

  public static UserType getType(final String type) {
    return Arrays.asList(UserType.values()).stream().filter(p -> p.name().equalsIgnoreCase(type)).findAny().orElse(null);
  }

  public String getValue() {
    return value;
  }

  public String getDesc() {
    return desc;
  }
}
