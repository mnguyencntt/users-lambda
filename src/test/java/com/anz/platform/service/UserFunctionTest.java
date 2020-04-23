package com.anz.platform.service;

import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import com.anz.platform.base.BaseTest;
import com.anz.platform.config.AppConfig;
import com.anz.platform.config.AppConfigMock;

public class UserFunctionTest extends BaseTest {
  private AppConfigMock appConfigMock = new AppConfigMock();

  @Ignore
  @Test
  public void testUser() {
    UserFunction userFunction = new UserFunction();
    AppConfig appConfig = new AppConfig();
    userFunction.setAppConfig(appConfig);
    userFunction.setDbInfo(appConfigMock.getDbInfo());
  }
}
