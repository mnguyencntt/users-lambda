package com.anz.platform.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import com.amazonaws.services.lambda.runtime.Context;
import com.anz.platform.config.AppConfig;
import com.anz.platform.domain.ApiResponse;
import com.anz.platform.domain.DbInfo;
import com.anz.platform.domain.UserRequest;
import com.anz.platform.domain.UserResponse;
import com.anz.platform.exception.UserException;
import com.anz.platform.model.Users;
import com.anz.platform.util.Constants;
import com.anz.platform.util.JsonUtils;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Setter
public class UserFunction {
  private AppConfig appConfig = new AppConfig();
  private DbInfo dbInfo = appConfig.getDbInfo();

  /*
   * submitUser
   */
  public ApiResponse submitUser(final UserRequest request, final Context context) {
    try {
      log.info("Request Data: {}", request);
      final UserService userService = new UserService(dbInfo);

      final Users user = Users.builder()
          .id(UUID.randomUUID().toString())
          .receiverUserId(request.getRecieverId())
          .status(Constants.STATUS_000)
          .message(Constants.DELIVERY_SEND_SUCCESS)
          .request(JsonUtils.toJson(request))
          .build();
      user.persist();

      final Integer updatedCount = userService.persist(user);
      if (updatedCount > 0) {
        log.info("Response Data: {}", user);
        return ApiResponse.build(Constants.STATUS_000, UserResponse.buildResponse(user, String.valueOf(11111)), Constants.DELIVERY_SEND_SUCCESS);
      } else {
        throw new UserException(Constants.DELIVERY_PERSIST_FAILED);
      }
    } catch (Exception e) {
      log.error(e.getMessage());
      throw new UserException(e.getMessage());
    }
  }

  /*
   * findUser
   */
  public ApiResponse findUser(final UserRequest request, final Context context) {
    try {
      log.info("Request Data: {}", request);
      final UserService userService = new UserService(dbInfo);
      final Users user = userService.findById(request.getUserId(), Users.class);
      log.info("Response Data: {}", user);
      return ApiResponse.build(Constants.STATUS_000, user, Constants.DELIVERY_FOUND);
    } catch (Exception e) {
      log.error(e.getMessage());
      return ApiResponse.build(Constants.STATUS_999, null, Constants.DELIVERY_NOT_FOUND);
    }
  }

  /*
   * findUsers
   */
  public ApiResponse findUsers(final UserRequest request, final Context context) {
    try {
      log.info("Request Data: {}", request);
      final UserService userService = new UserService(dbInfo);
      final List<Users> users = userService.findAll(Users.class);
      users.sort(Comparator.comparing(Users::getUpdatedAt));
      log.info("Response Data: {}", users);
      return ApiResponse.build(Constants.STATUS_000, users, Constants.DELIVERY_FOUND);
    } catch (Exception e) {
      log.error(e.getMessage());
      return ApiResponse.build(Constants.STATUS_999, Collections.emptyList(), Constants.DELIVERY_NOT_FOUND);
    }
  }
}
