package com.anz.platform.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.junit.jupiter.api.Test;
import com.anz.platform.base.BaseTest;
import com.anz.platform.config.AppConfigMock;
import com.anz.platform.domain.DbInfo;
import com.anz.platform.model.Users;
import com.anz.platform.util.ObjectUtils;

public class UserServiceTest extends BaseTest {

  private final DbInfo dbInfo = new AppConfigMock().getDbInfo();
  private final UserService userService = new UserService(dbInfo);

  @Test
  public void loadSqlFile() throws Exception {
    File file = getFileFromResources("file/users.sql");
    String printFile = printFile(file);
    // System.out.println(printFile);
    assertTrue(ObjectUtils.isNotEmpty(printFile));
  }

  @Test
  public void testUserDbConnection() throws SQLException, ClassNotFoundException {
    final DbInfo dbInfo = new AppConfigMock().getDbInfo();

    Class.forName(dbInfo.getSqlDriver());
    final QueryRunner run = new QueryRunner();
    final Connection conn = DriverManager.getConnection(dbInfo.getEndpoint(), dbInfo.getUsername(), dbInfo.getPassword());
    try {
      List<Map<String, Object>> maps = run.query(conn, "SELECT * FROM users", new MapListHandler());
      // System.out.println(maps);
      assertEquals(1, maps.size());
    } finally {
      DbUtils.close(conn);
    }
  }

  @Test
  public void testFindAllUsers() {
    List<Users> users = userService.findAll(Users.class);
    // System.out.println(users);
    assertEquals(1, users.size());
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
    user.persist();
    assertEquals(new Integer(1), userService.persist(user));
  }

  @Test
  public void testUpdateUser() {
    final Users user =
        Users.builder().id("12345").username("testuser2").password("testpassword2").name("MinhNguyen").dateOfBirth("01/01/1991").gender("Male").isActivated("TRUE").userType("SELLER").build();
    user.setRequest("request-json-format");
    user.persist();
    assertEquals(new Integer(1), userService.updateById(user));
  }
}
