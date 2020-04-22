package com.anz.platform.domain;

import java.util.Map;
import com.anz.platform.enumeration.UserFunctionType;
import com.anz.platform.enumeration.UserType;
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
  private String dob;
  private String gender;
  private String isActivated;
  private UserType userType;
  private String lastLogin;
  private String imageAvatarUrl;

  // aAdress info
  private AddressRequest baseAddress;
  private AddressRequest billingAddress;
  private AddressRequest deliveryAddress;


  // Function-Type
  private UserFunctionType functionType; // SEND - UPDATE - FIND - FINDALL - DELETE
  private Map<String, String> additionalFields;

}
