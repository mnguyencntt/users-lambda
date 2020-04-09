package com.anz.platform.config;

import com.anz.platform.domain.DbInfo;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Setter
public class AppConfig {
  private DbInfo dbInfo;

  public DbInfo getDbInfo() {
    String dbEndpoint = System.getenv("db_endpoint");
    String dbUsername = System.getenv("db_username");
    String dbPassword = System.getenv("db_password");
    String dbSqlDriver = System.getenv("db_sqlDriver"); // com.mysql.cj.jdbc.Driver - com.mysql.jdbc.Driver
    dbInfo = new DbInfo(dbEndpoint, dbUsername, dbPassword, dbSqlDriver);
    log.info(dbInfo.toString());
    return dbInfo;
  }
}
