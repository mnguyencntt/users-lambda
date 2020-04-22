package com.anz.platform.service;

import java.util.List;
import java.util.UUID;
import org.junit.Ignore;
import org.junit.Test;
import com.anz.platform.config.AppConfigMock;
import com.anz.platform.model.Users;

public class UserServiceTest {
  @Ignore
  @Test
  public void testUser() {
    final UserService userService = new UserService(new AppConfigMock().getDbInfo());

    final Users user = Users.builder().id(UUID.randomUUID().toString()).username("testuser").password("testpassword").build();
    user.setRequest("request");
    user.persist();

    System.out.println(userService.persist(user));
    List<Users> users = userService.findAll(Users.class);
    System.out.println(users.size());
    System.out.println(users);
  }
}
