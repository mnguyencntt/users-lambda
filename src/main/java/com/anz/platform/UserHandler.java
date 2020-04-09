package com.anz.platform;

import static com.anz.platform.util.Constants.DELIVERY_FUNCTION_NOT_SUPPORT;
import static com.anz.platform.util.Constants.STATUS_101;
import static com.anz.platform.util.Constants.STATUS_999;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.anz.platform.domain.ApiResponse;
import com.anz.platform.domain.UserRequest;
import com.anz.platform.enumeration.UserFunctionType;
import com.anz.platform.exception.InvalidRequestException;
import com.anz.platform.service.UserFunction;
import com.anz.platform.util.Constants;
import com.anz.platform.util.ObjectUtils;
import lombok.Setter;

@Setter
public class UserHandler implements RequestHandler<UserRequest, ApiResponse> {
  public ApiResponse handleRequest(final UserRequest request, final Context context) {
    try {
      if (ObjectUtils.isEmpty(request.getFunctionType())) {
        throw new InvalidRequestException(String.format(Constants.NOT_EMPTY, "functionType"));
      }

      final UserFunction function = new UserFunction();
      if (UserFunctionType.SEND.equals(request.getFunctionType())) {
        return function.submitUser(request, context);
      } else if (UserFunctionType.FIND.equals(request.getFunctionType())) {
        return function.findUser(request, context);
      } else if (UserFunctionType.FINDALL.equals(request.getFunctionType())) {
        return function.findUsers(request, context);
      } else {
        throw new InvalidRequestException(String.format(DELIVERY_FUNCTION_NOT_SUPPORT, request.getFunctionType()));
      }
    } catch (InvalidRequestException e) {
      return ApiResponse.build(STATUS_101, null, e.getMessage());
    } catch (Exception e) {
      return ApiResponse.build(STATUS_999, null, e.getMessage());
    }
  }
}
