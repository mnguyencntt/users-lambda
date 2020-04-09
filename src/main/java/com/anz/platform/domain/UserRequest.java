package com.anz.platform.domain;

import java.util.Map;
import com.anz.platform.enumeration.UserFunctionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {
  // Query
  private String userId;

  // Sender
  private String senderId;
  private String orderId;
  private String eventStatus; // ORDER_CREATED - DELIVERY_CREATED - DELIVERY_COMPLETED

  // Reciever
  private String recieverId;
  private String smsNumber;
  private String sesEmail;

  // Function-Type
  private UserFunctionType functionType; // SEND - UPDATE - FIND - FINDALL - DELETE
  private Map<String, String> additionalFields;

}
