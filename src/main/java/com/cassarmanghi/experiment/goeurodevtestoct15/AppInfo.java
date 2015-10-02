package com.cassarmanghi.experiment.goeurodevtestoct15;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class AppInfo {

  private static final String appPropsResourcePath
          = "/com/cassarmanghi/experiment/goeurodevtestoct15/app.properties";

  private static class PropKeys {

    private PropKeys() {
    }

    public static final String VERSION = "version";
    public static final String BUILD_DATE = "build.date";
    public static final String BUILD_DATE_FORMAT = "build.date.format";
  }

  private final String version;
  private final String buildDateStr;
  private final Date buildDate;
  private final String dateFormat;

  private AppInfo(final String version, final String buildDateStr,
          final Date buildDate, final String dateFormat) {
    this.version = version;
    this.buildDateStr = buildDateStr;
    this.buildDate = buildDate;
    this.dateFormat = dateFormat;
  }

  public static AppInfo load() throws IOException, ParseException {
    final Properties props = new Properties();
    try (final InputStream is = AppInfo.class.getResourceAsStream(
            appPropsResourcePath)) {
      props.load(is);
      final String dateFormat = props.getProperty(PropKeys.BUILD_DATE_FORMAT);
      final SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
      final String buildDateStr = props.getProperty(PropKeys.BUILD_DATE);
      return new AppInfo(props.getProperty(PropKeys.VERSION),
              buildDateStr, sdf.parse(buildDateStr), dateFormat);
    }
  }

  public String getVersion() {
    return version;
  }

  public String getBuildDateStr() {
    return buildDateStr;
  }

  public static String getAppPropsResourcePath() {
    return appPropsResourcePath;
  }

  public Date getBuildDate() {
    return buildDate;
  }

  public String getDateFormat() {
    return dateFormat;
  }
}
