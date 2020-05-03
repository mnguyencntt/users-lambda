package com.anz.platform.service;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import com.anz.platform.base.BaseTest;
import com.anz.platform.config.AppConfig;
import com.anz.platform.config.AppConfigMock;

public class UserFunctionTest extends BaseTest {
  private AppConfigMock appConfigMock = new AppConfigMock();

  @SuppressWarnings("unused")
  @Disabled
  @Test
  public void testUser() {
    UserFunction userFunction = new UserFunction(appConfigMock.getDbInfo());
    AppConfig appConfig = new AppConfig();
  }
}
