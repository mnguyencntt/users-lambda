package com.anz.platform.config;

import com.anz.platform.domain.DbInfo;
import com.anz.platform.util.Constants;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AppConfigMock {
  public DbInfo getDbInfo() {
    final String dbEndpoint = "jdbc:mysql://anz-platform-instance2.***.ap-southeast-2.rds.amazonaws.com:3306/users";
    final String dbUsername = "***";
    final String dbPassword = "***";
    DbInfo dbInfo = new DbInfo(dbEndpoint, dbUsername, dbPassword, Constants.EMPTY);
    log.info(dbInfo.toString());
    return dbInfo;
  }
}
