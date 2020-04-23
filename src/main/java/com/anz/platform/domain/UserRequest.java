package com.anz.platform.domain;

import static com.anz.platform.util.Constants.DDMMYYYY_HHMMSS;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;
import org.apache.commons.beanutils.BeanUtils;
import com.anz.platform.enumeration.UserFunctionType;
import com.anz.platform.enumeration.UserType;
import com.anz.platform.model.Users;
import com.anz.platform.util.DatetimeUtils;
import com.anz.platform.util.JsonUtils;
import com.anz.platform.util.ObjectUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {
  // Query
  private String userId;

  // Authentication info
  private String username;
  private String password;

  // User info
  private String name;
  private String dateOfBirth;
  private String gender;
  private String isActivated;
  private UserType userType;
  private String lastLogin;
  private String imageAvatarUrl;

  // Address info
  private AddressInfo baseAddress;
  private AddressInfo billingAddress;
  private AddressInfo deliveryAddress;

  // Function-Type
  private UserFunctionType functionType; // SEND - UPDATE - FIND - FINDALL - DELETE
  private Map<String, String> additionalFields;

  public Users buildUsers() throws IllegalAccessException, InvocationTargetException {
    final Users user = new Users();
    BeanUtils.copyProperties(this, user);
    user.setId(ObjectUtils.isEmpty(this.getUserId()) ? UUID.randomUUID().toString() : this.getUserId());
    user.setBaseAddress(JsonUtils.toJson(baseAddress));
    user.setBillingAddress(JsonUtils.toJson(billingAddress));
    user.setDeliveryAddress(JsonUtils.toJson(deliveryAddress));
    user.setRequest(JsonUtils.toJson(this));
    user.setLastLogin(DatetimeUtils.formatDateTime(LocalDateTime.now(), DDMMYYYY_HHMMSS));
    user.setUserType(this.getUserType().name());
    return user;
  }

}
