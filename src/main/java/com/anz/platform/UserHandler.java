package com.anz.platform;

import static com.anz.platform.util.Constants.STATUS_400;
import static com.anz.platform.util.Constants.STATUS_999;
import static com.anz.platform.util.Constants.USER_FUNCTION_NOT_SUPPORT;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.anz.platform.config.AppConfig;
import com.anz.platform.domain.ApiResponse;
import com.anz.platform.domain.DbInfo;
import com.anz.platform.domain.UserRequest;
import com.anz.platform.enumeration.UserFunctionType;
import com.anz.platform.exception.InvalidRequestException;
import com.anz.platform.service.UserFunction;
import com.anz.platform.util.Constants;
import com.anz.platform.util.ObjectUtils;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@NoArgsConstructor
public class UserHandler implements RequestHandler<UserRequest, ApiResponse> {
  private DbInfo dbInfo = new AppConfig().getDbInfo();

  public UserHandler(final DbInfo dbInfo) {
    this.dbInfo = dbInfo;
  }

  public ApiResponse handleRequest(final UserRequest request, final Context context) {
    try {
      if (ObjectUtils.isEmpty(request.getFunctionType())) {
        throw new InvalidRequestException(String.format(Constants.NOT_EMPTY, "functionType"));
      }

      final UserFunction function = new UserFunction(dbInfo);
      if (UserFunctionType.CREATE.equals(request.getFunctionType())) {
        return function.createUser(request, context);
      } else if (UserFunctionType.UPDATE.equals(request.getFunctionType())) {
        return function.updateUser(request, context);
      } else if (UserFunctionType.AUTHENTICATE.equals(request.getFunctionType())) {
        return function.authenticateUser(request, context);
      } else if (UserFunctionType.FINDID.equals(request.getFunctionType())) {
        return function.findUserById(request, context);
      } else if (UserFunctionType.FINDUSERNAME.equals(request.getFunctionType())) {
        return function.findUserByUsername(request, context);
      } else if (UserFunctionType.FINDALL.equals(request.getFunctionType())) {
        return function.findUsers(request, context);
      } else {
        throw new InvalidRequestException(String.format(USER_FUNCTION_NOT_SUPPORT, request.getFunctionType()));
      }
    } catch (InvalidRequestException e) {
      return ApiResponse.build(STATUS_400, null, e.getMessage());
    } catch (Exception e) {
      return ApiResponse.build(STATUS_999, null, e.getMessage());
    }
  }
}
