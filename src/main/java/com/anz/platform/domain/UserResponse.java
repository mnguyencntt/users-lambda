package com.anz.platform.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
  private String id;

  // Authentication info
  private String username;
  private String password;

  // Authentication token_id
  private String tokenId;
}
