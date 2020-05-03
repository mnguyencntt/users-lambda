package com.anz.platform.util;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DatetimeUtilsTest {
  @Test
  public void testConstructor() {
    Assertions.assertThrows(UnsupportedOperationException.class, () -> {
      new DatetimeUtils();
    });
  }

  @Test
  public void testNowLocalDate() {
    assertNotNull(DatetimeUtils.nowLocalDateTime());
    assertNotNull(DatetimeUtils.nowLocalDate());
    assertNotNull(DatetimeUtils.nowZonedDateTime());
  }

  @Test
  public void testFormatDate() {
    assertNull(DatetimeUtils.formatDate("", "dd/MM/yyyy"));
    assertNotNull(DatetimeUtils.formatDate("12/12/2012", "dd/MM/yyyy"));
    assertNotNull(DatetimeUtils.formatDate(LocalDate.now(), "dd/MM/yyyy"));
    // assertNotNull(DatetimeUtils.formatDateTime("12/12/2012", "dd/MM/yyyy"));
    assertNotNull(DatetimeUtils.convertStrToTimestamp("12/12/2012", "dd/MM/yyyy"));
    assertNull(DatetimeUtils.formatDateTime("", "dd/MM/yyyy"));
    assertNotNull(DatetimeUtils.formatDateTime(LocalDateTime.now(), "dd/MM/yyyy"));
    assertNotNull(DatetimeUtils.formatDateTime(ZonedDateTime.now(), "dd/MM/yyyy"));
  }
}
