package com.anz.platform.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Collections;
import java.util.List;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import com.anz.platform.domain.DbInfo;
import com.anz.platform.model.Users;
import com.anz.platform.util.Constants;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Setter
public class UserService {
  private DbInfo dbInfo;

  public UserService(final DbInfo dbInfo) {
    this.dbInfo = dbInfo;
  }

  public <T> List<T> findAll(Class<T> clazz) {
    log.info(Constants.INITIALIZE_CONNECTION);
    try (final Connection connection = DriverManager.getConnection(dbInfo.getEndpoint(), dbInfo.getUsername(), dbInfo.getPassword())) {
      log.info(Constants.SUCCESSFUL_CONNECTION);
      final ResultSetHandler<List<T>> resultHandler = new BeanListHandler<>(clazz);
      try {
        final String selectAll = "SELECT * FROM Users";
        final List<T> items = new QueryRunner().query(connection, selectAll, resultHandler);
        log.info("Found {} Items", items.size());
        return items;
      } finally {
        DbUtils.close(connection);
      }
    } catch (Exception e) {
      log.info(e.getMessage());
      return Collections.emptyList();
    }
  }

  public <T> T findById(final String id, Class<T> clazz) {
    log.info(Constants.INITIALIZE_CONNECTION);
    try (final Connection connection = DriverManager.getConnection(dbInfo.getEndpoint(), dbInfo.getUsername(), dbInfo.getPassword())) {
      log.info(Constants.SUCCESSFUL_CONNECTION);
      final ResultSetHandler<T> resultHandler = new BeanHandler<>(clazz);
      try {
        final String selectById = "SELECT * FROM Users WHERE id = ?";
        T query = new QueryRunner().query(connection, selectById, resultHandler, id);
        log.info("Found Item by '{}'", id);
        return query;
      } finally {
        DbUtils.close(connection);
      }
    } catch (Exception e) {
      log.info(e.getMessage());
      return null;
    }
  }

  public <T> T findByField(final String fieldName, final String fieldValue, Class<T> clazz) {
    log.info(Constants.INITIALIZE_CONNECTION);
    try (final Connection connection = DriverManager.getConnection(dbInfo.getEndpoint(), dbInfo.getUsername(), dbInfo.getPassword())) {
      log.info(Constants.SUCCESSFUL_CONNECTION);
      final ResultSetHandler<T> resultHandler = new BeanHandler<>(clazz);
      try {
        final String selectByField = String.format("SELECT * FROM Users WHERE %s = ?", fieldName);
        T query = new QueryRunner().query(connection, selectByField, resultHandler, fieldValue);
        log.info("Found Item by '{}'", fieldValue);
        return query;
      } finally {
        DbUtils.close(connection);
      }
    } catch (Exception e) {
      log.info(e.getMessage());
      return null;
    }
  }

  // INSERT INTO table_name (column1, column2, column3, ...)
  // VALUES (value1, value2, value3, ...);
  public Integer persist(final Users item) {
    log.info(Constants.INITIALIZE_CONNECTION);
    try (final Connection connection = DriverManager.getConnection(dbInfo.getEndpoint(), dbInfo.getUsername(), dbInfo.getPassword())) {
      log.info(Constants.SUCCESSFUL_CONNECTION);
      try {
        final String insertSQL = String.format("INSERT INTO %s (%s) VALUES (%s)", item.getClass().getSimpleName(), item.findFieldsJoining(), item.findMarksJoining());
        log.info(insertSQL);
        final Object[] inputValues = item.findValues();
        return new QueryRunner().update(connection, insertSQL, inputValues);
      } finally {
        DbUtils.close(connection);
      }
    } catch (Exception e) {
      log.info(e.getMessage());
      return null;
    }
  }

  // UPDATE table_name
  // SET column1 = value1, column2 = value2, ...
  // WHERE condition;
  public Integer updateById(final Users item) {
    log.info(Constants.INITIALIZE_CONNECTION);
    try (final Connection connection = DriverManager.getConnection(dbInfo.getEndpoint(), dbInfo.getUsername(), dbInfo.getPassword())) {
      log.info(Constants.SUCCESSFUL_CONNECTION);
      try {
        final String updateSQL = String.format("Update %s SET %s WHERE Id=%s", item.getClass().getSimpleName(), item.findFieldValuesJoining("Id"), item.getId());
        log.info(updateSQL);
        return new QueryRunner().update(connection, updateSQL);
      } finally {
        DbUtils.close(connection);
      }
    } catch (Exception e) {
      log.info(e.getMessage());
      return null;
    }
  }
}
