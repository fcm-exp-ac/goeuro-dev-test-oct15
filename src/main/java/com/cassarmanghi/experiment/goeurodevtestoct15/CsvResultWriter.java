package com.cassarmanghi.experiment.goeurodevtestoct15;

import com.cassarmanghi.experiment.goeurodevtestoct15.pojos.LocationInfo;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class CsvResultWriter {

  public static class FileExistsException extends Exception {

    public FileExistsException() {
    }

    public FileExistsException(final String message) {
      super(message);
    }

    public FileExistsException(final String message, final Throwable tr) {
      super(message, tr);
    }

    public FileExistsException(final Throwable tr) {
      super(tr);
    }
  }

  public static void outputLocations(final List<LocationInfo> locations,
          final String csvOutFile) throws FileExistsException, IOException {
    final File f = new File(csvOutFile);
    if (!f.createNewFile()) {
      throw new FileExistsException(String.format("File [%s] already exists.",
              csvOutFile));
    }
    try (final FileOutputStream fos = new FileOutputStream(f);
            final OutputStreamWriter osw = new OutputStreamWriter(fos,
                    StandardCharsets.UTF_8);
            final BufferedWriter fw = new BufferedWriter(osw)) {
      fw.write(String.format("_id,name,type,latitude,longitude%n"));
      for (final LocationInfo li : locations) {
        fw.write(String.format("%d,\"%s\",\"%s\",%s,%s%n", li.getId(),
                li.getName(), li.getType(), li.getGeoPosition() != null
                        ? li.getGeoPosition().getLatitude() : "",
                li.getGeoPosition() != null
                        ? li.getGeoPosition().getLongitude() : ""));
      }
      fw.flush();
    }
  }
}
