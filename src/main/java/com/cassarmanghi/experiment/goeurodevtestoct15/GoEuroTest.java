package com.cassarmanghi.experiment.goeurodevtestoct15;

import java.io.IOException;
import java.text.ParseException;

public class GoEuroTest {

  private static AppInfo appInfo = null;

  public static void main(String[] args) {
    try {
      appInfo = AppInfo.load();
    } catch (final IOException | ParseException ex) {
      System.err.println("Failed to load internal AppInfo. Application will "
              + "exit.");
      throw new RuntimeException(ex);
    }
    System.out.println(String.format("Starting GoEuroTest version: %s (built on:"
            + " %s)", appInfo.getVersion(), appInfo.getBuildDate()));
  }
}
