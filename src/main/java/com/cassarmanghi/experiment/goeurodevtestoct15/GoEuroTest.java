package com.cassarmanghi.experiment.goeurodevtestoct15;

import com.cassarmanghi.experiment.goeurodevtestoct15.ApiCaller.ApiCallException;
import com.cassarmanghi.experiment.goeurodevtestoct15.CsvResultWriter.FileExistsException;
import com.cassarmanghi.experiment.goeurodevtestoct15.pojos.LocationInfo;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    if (args.length != 2) {
      System.err.println(String.format("ERROR: Incorrect number of parameters "
              + "supplied. Found %d expected 2.", args.length));
      printHelpMessage();
      System.exit(-1);
    }
    final String cityName = args[0];
    final String csvOutFile = args[1];
    final List<LocationInfo> locations;
    try {
      locations = ApiCaller.call(cityName);
    } catch (final ApiCallException | IOException ex) {
      System.err.println("Failed to call API. Application will no exit as there"
              + " is nothing else it can do.");
      throw new RuntimeException(ex);
    }
    if (locations == null || locations.isEmpty()) {
      System.out.println(String.format("No locations found for query [%s]. "
              + "Exiting.", cityName));
      return;
    }
    try {
      CsvResultWriter.outputLocations(locations, csvOutFile);
    } catch (FileExistsException ex) {
      System.err.println(ex.getMessage());
      System.exit(-1);
    } catch (IOException ex) {
      System.err.println("IO Error whilst writing to CSV file.");
      throw new RuntimeException(ex);
    }
    System.out.println("Finished.");
  }

  private static void printHelpMessage() {
    System.out.println(String.format("%nUsage:%n%n\tjava -jar %s [CITY_NAME] "
            + "[CSV_FILE_OUTPUT_PATH]%n\texample: java -jar %s BERLIN "
            + "/home/test/data_berlin.csv", appInfo.getJarName(),
            appInfo.getJarName()));
  }
}
