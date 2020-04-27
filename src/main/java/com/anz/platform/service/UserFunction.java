package com.anz.platform.service;

import static com.anz.platform.model.Users.USERNAME;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import com.amazonaws.services.lambda.runtime.Context;
import com.anz.platform.domain.ApiResponse;
import com.anz.platform.domain.DbInfo;
import com.anz.platform.domain.UserRequest;
import com.anz.platform.exception.UserException;
import com.anz.platform.model.Users;
import com.anz.platform.util.Constants;
import com.anz.platform.util.ObjectUtils;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Setter
public class UserFunction {
  private DbInfo dbInfo;

  public UserFunction(final DbInfo dbInfo) {
    this.dbInfo = dbInfo;
  }

  /*
   * createUser
   */
  public ApiResponse createUser(final UserRequest request, final Context context) {
    try {
      log.info("Request Data: {}", request);
      final UserService userService = new UserService(dbInfo);

      ApiResponse findUserByUsername = findUserByUsername(request, context);
      if (Constants.STATUS_000.equalsIgnoreCase(findUserByUsername.getStatus())) {
        return ApiResponse.build(Constants.STATUS_409, Constants.USER_EXISTING);
      }

      final Users user = request.buildUsers();

      final Integer updatedCount = userService.persist(user);
      if (updatedCount > 0) {
        log.info("Response Data: {}", user);
        return ApiResponse.build(Constants.STATUS_000, user);
      } else {
        throw new UserException(Constants.USER_PERSIST_FAILED);
      }
    } catch (Exception e) {
      log.error(e.getMessage());
      throw new UserException(e.getMessage());
    }
  }

  /*
   * updateUser
   */
  public ApiResponse updateUser(final UserRequest request, final Context context) {
    try {
      log.info("Request Data: {}", request);
      final UserService userService = new UserService(dbInfo);

      ApiResponse findUserByUsername = findUserByUsername(request, context);
      if (!Constants.STATUS_000.equalsIgnoreCase(findUserByUsername.getStatus())) {
        return ApiResponse.build(Constants.STATUS_404, Constants.USER_NOT_EXISTING);
      }

      Users userExisting = (Users) findUserByUsername.getData();
      final Users user = request.buildUsers();
      user.setId(userExisting.getId());

      final Integer updatedCount = userService.updateById(user);
      if (updatedCount > 0) {
        log.info("Response Data: {}", user);
        return ApiResponse.build(Constants.STATUS_000, user);
      } else {
        throw new UserException(Constants.USER_PERSIST_FAILED);
      }
    } catch (Exception e) {
      log.error(e.getMessage());
      throw new UserException(e.getMessage());
    }
  }

  /*
   * authenticateUser
   */
  public ApiResponse authenticateUser(final UserRequest request, final Context context) {
    try {
      log.info("Request Data: {}", request);
      final UserService userService = new UserService(dbInfo);
      final Users user = userService.findByField(USERNAME, request.getUsername(), Users.class);
      log.info("Response Data: {}", user);
      if (ObjectUtils.isEmpty(user)) {
        return ApiResponse.build(Constants.STATUS_404, user, Constants.USER_NOT_FOUND);
      }
      return ApiResponse.build(Constants.STATUS_000, user, Constants.USER_FOUND);
    } catch (Exception e) {
      log.error(e.getMessage());
      return ApiResponse.build(Constants.STATUS_999, null, Constants.USER_NOT_FOUND);
    }
  }

  /*
   * findUser
   */
  public ApiResponse findUserById(final UserRequest request, final Context context) {
    try {
      log.info("Request Data: {}", request);
      final UserService userService = new UserService(dbInfo);
      final Users user = userService.findById(request.getUserId(), Users.class);
      log.info("Response Data: {}", user);
      if (ObjectUtils.isEmpty(user)) {
        return ApiResponse.build(Constants.STATUS_404, user, Constants.USER_NOT_FOUND);
      }
      return ApiResponse.build(Constants.STATUS_000, user, Constants.USER_FOUND);
    } catch (Exception e) {
      log.error(e.getMessage());
      return ApiResponse.build(Constants.STATUS_999, null, Constants.USER_NOT_FOUND);
    }
  }

  /*
   * findUserByUsername
   */
  public ApiResponse findUserByUsername(final UserRequest request, final Context context) {
    try {
      log.info("Request Data: {}", request);
      final UserService userService = new UserService(dbInfo);
      final Users user = userService.findByField(USERNAME, request.getUsername(), Users.class);
      log.info("Response Data: {}", user);
      if (ObjectUtils.isEmpty(user)) {
        return ApiResponse.build(Constants.STATUS_404, user, Constants.USER_NOT_FOUND);
      }
      return ApiResponse.build(Constants.STATUS_000, user, Constants.USER_FOUND);
    } catch (Exception e) {
      log.error(e.getMessage());
      return ApiResponse.build(Constants.STATUS_999, null, Constants.USER_NOT_FOUND);
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
      if (ObjectUtils.isEmpty(users)) {
        return ApiResponse.build(Constants.STATUS_404, users, Constants.USER_NOT_FOUND);
      }
      return ApiResponse.build(Constants.STATUS_000, users, Constants.USER_FOUND);
    } catch (Exception e) {
      log.error(e.getMessage());
      return ApiResponse.build(Constants.STATUS_999, Collections.emptyList(), Constants.USER_NOT_FOUND);
    }
  }
}
