package com.anz.platform.base;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.junit.jupiter.api.Test;
import com.amazonaws.services.lambda.runtime.Context;
import com.anz.platform.config.AppConfigMock;
import com.anz.platform.domain.DbInfo;
import com.anz.platform.model.Users;
import com.anz.platform.util.Constants;
import com.anz.platform.util.JsonUtils;
import com.anz.platform.util.ObjectUtils;
import com.fasterxml.jackson.core.type.TypeReference;

public abstract class BaseTest {
  protected static final String PATH_ORDERS_JSON = "/files/Users.json";

  protected List<Users> prepareOrders() throws URISyntaxException, IOException {
    final String ordersJson = getMockContent(PATH_ORDERS_JSON);
    List<Users> orders = JsonUtils.toObject(ordersJson, new TypeReference<List<Users>>() {});
    return orders;
  }

  protected Context createContext() {
    TestContext context = new TestContext();
    context.setFunctionName("anz-platform-report");
    return context;
  }

  protected String getMockContent(final String path) throws URISyntaxException, IOException {
    URI respURI = ClassLoader.class.getResource(path).toURI();
    return new String(Files.readAllBytes(Paths.get(respURI)));
  }

  protected File getFileFromResources(String fileName) {
    ClassLoader classLoader = getClass().getClassLoader();
    URL resource = classLoader.getResource(fileName);
    if (resource == null) {
      throw new IllegalArgumentException("file is not found!");
    } else {
      return new File(resource.getFile());
    }
  }

  protected static String printFile(File file) throws IOException {
    if (file == null)
      return Constants.EMPTY;
    StringBuffer strBuffer = new StringBuffer();
    try (FileReader reader = new FileReader(file); BufferedReader br = new BufferedReader(reader)) {
      String line;
      while ((line = br.readLine()) != null) {
        strBuffer.append(line + "\n");
      }
    }
    return strBuffer.toString();
  }

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
      assertEquals(2, maps.size());
    } finally {
      DbUtils.close(conn);
    }
  }
}
