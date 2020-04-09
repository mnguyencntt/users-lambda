package com.anz.platform.service;

import org.junit.Ignore;
import org.junit.Test;
import com.anz.platform.base.BaseTest;
import com.anz.platform.config.AppConfig;
import com.anz.platform.config.AppConfigMock;
import com.anz.platform.domain.ApiResponse;
import com.anz.platform.domain.UserRequest;
import com.anz.platform.util.JsonUtils;

public class UserFunctionTest extends BaseTest {
  private AppConfigMock appConfigMock = new AppConfigMock();

  @Ignore
  @Test
  public void testUser() {
    UserFunction userFunction = new UserFunction();
    AppConfig appConfig = new AppConfig();
    userFunction.setAppConfig(appConfig);
    userFunction.setDbInfo(appConfigMock.getDbInfo());

    String userRequestJson = "{\n" 
        + "  \"senderId\": \"UIB12345\",\n" 
        + "  \"orderId\": \"OI12345\",\n" 
        + "  \"userId\": \"DI12345\",\n" 
        + "  \"eventStatus\": \"ORDER_CREATED\",\n"
        + "  \"recieverId\": \"UIS12345\",\n" 
        + "  \"smsNumber\": \"\",\n" // +6593767011
        + "  \"sesEmail\": \"m.nguyencntt7891@gmail.com\",\n" 
        + "  \"functionType\": \"SEND\"\n" + "}";
    final UserRequest userRequest = JsonUtils.toObject(userRequestJson, UserRequest.class);
    ApiResponse submitUser = userFunction.submitUser(userRequest, createContext());
    System.out.println(submitUser);
    ApiResponse findUser = userFunction.findUser(UserRequest.builder().userId("d8ffe614-133c-4fc8-a4af-1e2b5a0331e9").build(), createContext());
    System.out.println(findUser);
    ApiResponse findUsers = userFunction.findUsers(new UserRequest(), createContext());
    System.out.println(findUsers);
  }
}
