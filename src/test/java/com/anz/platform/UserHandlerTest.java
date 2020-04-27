package com.anz.platform;

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
import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import com.anz.platform.base.BaseTest;
import com.anz.platform.config.AppConfigMock;
import com.anz.platform.domain.ApiResponse;
import com.anz.platform.domain.DbInfo;
import com.anz.platform.domain.UserRequest;
import com.anz.platform.util.JsonUtils;
import com.anz.platform.util.ObjectUtils;
 
@Ignore
public class UserHandlerTest extends BaseTest {
  private final UserHandler userHandler = new UserHandler();

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
  public void testUser() {
    String jsonRequesst = "{\n" + 
        "  \"username\": \"testuser1\",\n" + 
        "  \"password\": \"testpassword1\",\n" + 
        "  \"name\": \"MinhNguyen\",\n" + 
        "  \"dateOfBirth\": \"01/01/1991\",\n" + 
        "  \"gender\": \"Male\",\n" + 
        "  \"isActivated\": \"TRUE\",\n" + 
        "  \"userType\": \"SELLER\",\n" + 
        "  \"imageAvatarUrl\": \"https://www.google.com/MinhNguyenAvatar.png\",\n" + 
        "  \"functionType\": \"CREATE\"\n" + 
        "}";
    final UserRequest request  = JsonUtils.toObject(jsonRequesst, UserRequest.class);
    ApiResponse handleRequest = userHandler.handleRequest(request, createContext());
    System.out.println(handleRequest.toString());
  }
}
