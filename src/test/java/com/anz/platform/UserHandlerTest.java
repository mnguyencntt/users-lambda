package com.anz.platform;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import com.anz.platform.base.BaseTest;
import com.anz.platform.config.AppConfigMock;
import com.anz.platform.domain.ApiResponse;
import com.anz.platform.domain.DbInfo;
import com.anz.platform.domain.UserRequest;
import com.anz.platform.enumeration.UserFunctionType;
import com.anz.platform.util.JsonUtils;

public class UserHandlerTest extends BaseTest {
  private final DbInfo dbInfo = new AppConfigMock().getDbInfo();
  private final UserHandler userHandler = new UserHandler(dbInfo);

  private final String jsonRequesst =
      "{ \"username\": \"testuser1\", \"password\": \"testpassword1\", \"name\": \"MinhNguyen\", \"dateOfBirth\": \"01/01/1991\", \"gender\": \"Male\", \"isActivated\": \"TRUE\", \"userType\": \"SELLER\", \"imageAvatarUrl\": \"https://www.google.com/MinhNguyenAvatar.png\", \"functionType\": \"CREATE\"}";

  @Test
  public void testCreateUser_FAILED_Conflict() {
    final UserRequest request = JsonUtils.toObject(jsonRequesst, UserRequest.class);
    request.setUsername("testuser1");
    request.setFunctionType(UserFunctionType.CREATE);

    ApiResponse handleRequest = userHandler.handleRequest(request, createContext());
    System.out.println(handleRequest.toString());
    assertEquals("409", handleRequest.getStatus());
  }

  @Test
  public void testCreateUser_SUCCESS() {
    final UserRequest request = JsonUtils.toObject(jsonRequesst, UserRequest.class);
    request.setUsername("testuser2");
    request.setFunctionType(UserFunctionType.CREATE);

    ApiResponse handleRequest = userHandler.handleRequest(request, createContext());
    System.out.println(handleRequest.toString());
    assertEquals("000", handleRequest.getStatus());
  }

  @Test
  public void testUpdateUser_SUCCESS() {
    final UserRequest request = JsonUtils.toObject(jsonRequesst, UserRequest.class);
    request.setUserId("12345");
    request.setUsername("testuser1");
    request.setFunctionType(UserFunctionType.UPDATE);

    ApiResponse handleRequest = userHandler.handleRequest(request, createContext());
    System.out.println(handleRequest.toString());
    assertEquals("000", handleRequest.getStatus());
  }

  @Test
  public void testUpdateUser_FAILED_NotFound() {
    final UserRequest request = JsonUtils.toObject(jsonRequesst, UserRequest.class);
    request.setUsername("testuser2");
    request.setFunctionType(UserFunctionType.UPDATE);

    ApiResponse handleRequest = userHandler.handleRequest(request, createContext());
    System.out.println(handleRequest.toString());
    assertEquals("404", handleRequest.getStatus());
  }

  @Test
  public void testFindByUsername_NotFound() {
    final UserRequest request = JsonUtils.toObject(jsonRequesst, UserRequest.class);
    request.setUsername("testuser2");
    request.setFunctionType(UserFunctionType.FINDUSERNAME);

    ApiResponse handleRequest = userHandler.handleRequest(request, createContext());
    System.out.println(handleRequest.toString());
    assertEquals("404", handleRequest.getStatus());
  }

  @Test
  public void testFindByUsername() {
    final UserRequest request = JsonUtils.toObject(jsonRequesst, UserRequest.class);
    request.setUsername("testuser1");
    request.setFunctionType(UserFunctionType.FINDUSERNAME);

    ApiResponse handleRequest = userHandler.handleRequest(request, createContext());
    System.out.println(handleRequest.toString());
    assertEquals("000", handleRequest.getStatus());
  }

  @Test
  public void testAuthenticate() {
    final UserRequest request = JsonUtils.toObject(jsonRequesst, UserRequest.class);
    request.setFunctionType(UserFunctionType.AUTHENTICATE);

    ApiResponse handleRequest = userHandler.handleRequest(request, createContext());
    System.out.println(handleRequest.toString());
    assertEquals("000", handleRequest.getStatus());
  }

  @Test
  public void testFindUserById() {
    final UserRequest request = JsonUtils.toObject(jsonRequesst, UserRequest.class);
    request.setUserId("12345");
    request.setFunctionType(UserFunctionType.FINDID);

    ApiResponse handleRequest = userHandler.handleRequest(request, createContext());
    System.out.println(handleRequest.toString());
    assertEquals("000", handleRequest.getStatus());
  }

  @Test
  public void testFindAllUsers() {
    final UserRequest request = JsonUtils.toObject(jsonRequesst, UserRequest.class);
    request.setFunctionType(UserFunctionType.FINDALL);

    ApiResponse handleRequest = userHandler.handleRequest(request, createContext());
    System.out.println(handleRequest.toString());
    assertEquals("000", handleRequest.getStatus());
  }
}
