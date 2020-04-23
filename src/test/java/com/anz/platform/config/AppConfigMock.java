package com.anz.platform.config;

import com.anz.platform.domain.DbInfo;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AppConfigMock {
  public DbInfo getDbInfo() {
    // final String dbEndpoint = "jdbc:mysql://anz-platform-instance2.***.ap-southeast-2.rds.amazonaws.com:3306/users";
    // final String dbUsername = "***";
    // final String dbPassword = "***";

    final String dbEndpoint = "jdbc:h2:mem:;INIT=runscript from 'classpath:file/users.sql'";
    final String dbUsername = "sa";
    final String dbPassword = "";
    final String dbSqlDriver = "org.h2.Driver";

    DbInfo dbInfo = new DbInfo(dbEndpoint, dbUsername, dbPassword, dbSqlDriver);
    log.info(dbInfo.toString());
    return dbInfo;
  }
}
