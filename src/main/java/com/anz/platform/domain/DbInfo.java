package com.anz.platform.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DbInfo {
  private String endpoint;
  private String username;
  private String password;
  private String sqlDriver;
}
