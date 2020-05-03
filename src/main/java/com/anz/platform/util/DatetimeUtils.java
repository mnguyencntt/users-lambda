package com.anz.platform.util;

import static com.anz.platform.util.Constants.GMT;
import static com.anz.platform.util.Constants.ZONEID_SINGAPORE;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.TimeZone;

public final class DatetimeUtils {
  DatetimeUtils() {
    throw new UnsupportedOperationException();
  }

  public static LocalDateTime nowLocalDateTime() {
    return LocalDateTime.now(ZoneId.of(ZONEID_SINGAPORE));
  }

  public static LocalDate nowLocalDate() {
    return LocalDate.now(ZoneId.of(ZONEID_SINGAPORE));
  }

  public static ZonedDateTime nowZonedDateTime() {
    return ZonedDateTime.now(ZoneId.of(ZONEID_SINGAPORE));
  }

  public static String formatByTimezone(final String strDateTime, final String formatInput, final String formatOutput) {
    DateFormat df1 = new SimpleDateFormat(formatInput);
    df1.setTimeZone(TimeZone.getTimeZone(GMT));
    DateFormat df2 = new SimpleDateFormat(formatOutput);
    df2.setTimeZone(TimeZone.getTimeZone(GMT));
    try {
      Date date = df1.parse(strDateTime);
      return df2.format(date);
    } catch (ParseException e) {
      throw new IllegalArgumentException(e.getMessage());
    }
  }

  public static Timestamp convertStrToTimestamp(final String strDateTime, final String format) {
    DateFormat df = new SimpleDateFormat(format);
    df.setTimeZone(TimeZone.getTimeZone(GMT));
    try {
      Date date = df.parse(strDateTime);
      return new Timestamp(date.getTime());
    } catch (ParseException e) {
      throw new IllegalArgumentException(e.getMessage());
    }
  }

  public static LocalDate formatDate(final String str, final String srcDateFormat) {
    if (ObjectUtils.isEmpty(str))
      return null;
    return LocalDate.parse(str, DateTimeFormatter.ofPattern(srcDateFormat));
  }

  public static String formatDate(final LocalDate dateSrc, final String destDateFormat) {
    if (ObjectUtils.isEmpty(dateSrc))
      return null;
    return dateSrc.format(DateTimeFormatter.ofPattern(destDateFormat));
  }

  public static LocalDateTime formatDateTime(final String str, final String srcDateFormat) {
    if (ObjectUtils.isEmpty(str))
      return null;
    return LocalDateTime.parse(str, DateTimeFormatter.ofPattern(srcDateFormat));
  }

  public static String formatDateTime(final LocalDateTime dateSrc, final String destDateFormat) {
    if (ObjectUtils.isEmpty(dateSrc))
      return null;
    return dateSrc.format(DateTimeFormatter.ofPattern(destDateFormat));
  }

  public static String formatDateTime(final ZonedDateTime dateSrc, final String destDateFormat) {
    if (ObjectUtils.isEmpty(dateSrc))
      return null;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(destDateFormat);
    return dateSrc.format(formatter);
  }
}
