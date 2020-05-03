package com.anz.platform.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.List;
import org.junit.jupiter.api.Test;
import com.anz.platform.base.BaseTest;
import com.anz.platform.config.AppConfigMock;
import com.anz.platform.domain.DbInfo;
import com.anz.platform.model.Users;

public class UserServiceTest extends BaseTest {

  private final DbInfo dbInfo = new AppConfigMock().getDbInfo();
  private final UserService userService = new UserService(dbInfo);

  @Test
  public void testFindAllUsers() {
    List<Users> users = userService.findAll(Users.class);
    // System.out.println(users);
    assertEquals(2, users.size());
    assertEquals("12345", users.get(0).getId());
  }

  @Test
  public void testFindByIdUser() {
    Users user = userService.findById("12345", Users.class);
    // System.out.println(users);
    assertEquals("12345", user.getId());
  }

  @Test
  public void testFindByFieldUser() {
    Users user = userService.findByField("username", "testuser1", Users.class);
    // System.out.println(users);
    assertEquals("12345", user.getId());
  }

  @Test
  public void testPersistUser() {
    final Users user =
        Users.builder().id("54321").username("testuser2").password("testpassword2").name("MinhNguyen").dateOfBirth("01/01/1991").gender("Male").isActivated("TRUE").userType("SELLER").build();
    user.setRequest("request-json-format");
    user.persist("MinhNguyen");
    assertEquals(new Integer(1), userService.persist(user));
  }

  @Test
  public void testUpdateUser() {
    final Users user =
        Users.builder().id("12345").username("testuser2").password("testpassword2").name("MinhNguyen").dateOfBirth("01/01/1991").gender("Male").isActivated("TRUE").userType("SELLER").build();
    user.setRequest("request-json-format");
    user.persist("MinhNguyen");
    assertEquals(new Integer(1), userService.updateById(user));
  }
}
