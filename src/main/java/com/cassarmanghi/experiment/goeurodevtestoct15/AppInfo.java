package com.cassarmanghi.experiment.goeurodevtestoct15;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

/**
 * Provides meta-data relating to the main application executing.
 */
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
  private final String jarName;
  private final String jarPath;

  private AppInfo(final String version, final String buildDateStr,
          final Date buildDate, final String dateFormat, final String jarName,
          final String jarPath) {
    this.version = version;
    this.buildDateStr = buildDateStr;
    this.buildDate = buildDate;
    this.dateFormat = dateFormat;
    this.jarName = jarName;
    this.jarPath = jarPath;
  }

  /**
   * Loads the meta-data from the integrated app.properties file (populated by
   * Maven during the build process) and some other data.
   *
   * @return AppInfo instance.
   * @throws IOException If the app.properties file is missing or unreadable.
   * @throws ParseException If the timestamp from the app.properties file cannot
   * be parsed.
   */
  public static AppInfo load() throws IOException, ParseException {
    final Properties props = new Properties();
    try (final InputStream is = AppInfo.class.getResourceAsStream(
            appPropsResourcePath)) {
      props.load(is);
    }
    final String dateFormat = props.getProperty(PropKeys.BUILD_DATE_FORMAT);
    final SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
    final String buildDateStr = props.getProperty(PropKeys.BUILD_DATE);
    final String jarPath = AppInfo.class.getProtectionDomain().getCodeSource()
            .getLocation().getPath();
    final File jarFile = new File(jarPath);
    return new AppInfo(props.getProperty(PropKeys.VERSION),
            buildDateStr, sdf.parse(buildDateStr), dateFormat,
            jarFile.getName(), jarPath);
  }

  /**
   * Get the application version, as defined in the POM file.
   *
   * @return Application Version
   */
  public String getVersion() {
    return version;
  }

  /**
   * Get the application build timestamp as a string.
   *
   * @return Build Date as string.
   */
  public String getBuildDateStr() {
    return buildDateStr;
  }

  /**
   * Get the expected location of the app.properties file within the JAR.
   *
   * @return Expected location of app.properties file within JAR.
   */
  public static String getAppPropsResourcePath() {
    return appPropsResourcePath;
  }

  /**
   * Get the application build timestamp as a Date object.
   *
   * @return Build Date as Date object.
   */
  public Date getBuildDate() {
    return buildDate;
  }

  /**
   * Get the Date Format used for the build Timestamp.
   *
   * @return Date Format used for Build Date string.
   */
  public String getDateFormat() {
    return dateFormat;
  }

  /**
   * Get the main executable JAR file name.
   *
   * @return Executable JAR file name.
   */
  public String getJarName() {
    return jarName;
  }

  /**
   * Get the main executable JAR file location.
   *
   * @return Executable JAR file location.
   */
  public String getJarPath() {
    return jarPath;
  }
}
