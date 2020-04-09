package com.anz.platform.domain;

import com.anz.platform.model.Users;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
  private String buyerId;
  private String sellerId;
  private String receiverId;
  private String userId;
  private String userType;
  private String amount;

  public static UserResponse buildResponse(final Users user, final String amount) {
    return UserResponse.builder().receiverId(user.getReceiverUserId()).userId(user.getId()).userType(user.getUserType()).amount(amount).build();
  }
}
