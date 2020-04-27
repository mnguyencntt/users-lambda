package com.anz.platform.model;

import static com.anz.platform.util.Constants.DDMMYYYY_HHMMSS;
import java.time.LocalDateTime;
import com.anz.platform.model.base.BaseObject;
import com.anz.platform.util.DatetimeUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Users extends BaseObject {
  public static final String USERNAME = "username";

  private String id;

  // Authentication info
  private String username;
  private String password;

  private String name;
  private String dateOfBirth;
  private String gender;
  private String isActivated;
  private String userType;
  private String lastLogin;
  private String imageAvatarUrl;
  private String request; // store request of API

  private String baseAddress; // AddressRequest
  private String billingAddress; // AddressRequest
  private String deliveryAddress; // AddressRequest

  private String createdAt;
  private String createdBy;
  private String updatedAt;
  private String updatedBy;

  public void persist() {
    createdAt = DatetimeUtils.formatDateTime(LocalDateTime.now(), DDMMYYYY_HHMMSS);
    createdBy = "Anonymous";
    updatedAt = DatetimeUtils.formatDateTime(LocalDateTime.now(), DDMMYYYY_HHMMSS);
    updatedBy = "Anonymous";
  }

  public void update() {
    updatedAt = DatetimeUtils.formatDateTime(LocalDateTime.now(), DDMMYYYY_HHMMSS);
    updatedBy = "Anonymous";
  }
}
