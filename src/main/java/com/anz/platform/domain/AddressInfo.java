package com.anz.platform.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressInfo {
  private String addressId;

  private String fullAddress;
  private String postcode;
  private boolean isContactless;

  private String phoneNumber;
  private boolean isPhonePhoneVerified;

  private String email;
  private boolean isEmailVerified;
}
