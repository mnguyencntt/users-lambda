package com.anz.platform.util;

import static com.anz.platform.util.Constants.DOT;
import static com.anz.platform.util.Constants.EMPTY;
import static com.anz.platform.util.Constants.STAR;
import lombok.experimental.UtilityClass;

@UtilityClass
public class MaskerUtils {
  public String ignoreLast4(final String input) {
    return maskPrefix(input, 4);
  }

  public String ignoreFirst3(final String input) {
    return maskSuffix(input, 3);
  }

  public String maskPrefix(final String input, final int exceptLast) {
    return mask(input, exceptLast, true);
  }

  public String maskSuffix(final String input, final int exceptFirst) {
    return mask(input, exceptFirst, false);
  }

  private String mask(final String input, final int except, final boolean isPrefix) {
    if (ObjectUtils.isEmpty(input))
      return EMPTY;
    if (input.length() <= except)
      return input.replaceAll(DOT, STAR);
    if (isPrefix) {
      final String firstStr = input.substring(0, input.length() - except);
      final String secondStr = input.substring(input.length() - except);
      return firstStr.replaceAll(DOT, STAR) + secondStr;
    } else {
      final String firstStr = input.substring(0, except);
      final String secondStr = input.substring(except);
      return firstStr + secondStr.replaceAll(DOT, STAR);
    }
  }
}
