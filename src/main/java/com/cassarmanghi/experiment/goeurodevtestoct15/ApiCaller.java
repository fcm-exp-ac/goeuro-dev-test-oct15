package com.cassarmanghi.experiment.goeurodevtestoct15;

import com.cassarmanghi.experiment.goeurodevtestoct15.pojos.LocationInfo;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;

/**
 * Handles calls to the GoEuro API.
 */
public class ApiCaller {

  private static final String API_URL
          = "http://api.goeuro.com/api/v2/position/suggest/en/%s";

  public static class ApiCallException extends Exception {

    public ApiCallException() {
    }

    public ApiCallException(final String message) {
      super(message);
    }

    public ApiCallException(final String message, final Throwable tr) {
      super(message, tr);
    }

    public ApiCallException(final Throwable tr) {
      super(tr);
    }
  }

  /**
   * Calls the API for the given city and returns a list of LocationInfo
   * objects.
   *
   * @param cityName City Name to query with.
   * @return LocationInfo objects.
   * @throws
   * com.cassarmanghi.experiment.goeurodevtestoct15.ApiCaller.ApiCallException
   * Thrown if an error occurs whilst trying to call API.
   * @throws java.io.IOException If API call failed due to an IO error.
   */
  public static List<LocationInfo> call(final String cityName)
          throws ApiCallException, IOException {
    final String url;
    try {
      url = String.format(API_URL, URLEncoder.encode(cityName,
              StandardCharsets.UTF_8.name()));
    } catch (UnsupportedEncodingException ex) {
      throw new RuntimeException(String.format("%s is missing! This should "
              + "never happen for that encoding - check JVM!",
              StandardCharsets.UTF_8.name()), ex);
    }
    System.out.println(String.format("Querying for city [%s] on URL: %s",
            cityName, url));
    final HttpClient client = HttpClients.createDefault();
    final HttpGet httpGet = new HttpGet(url);
    final long tsBegin = System.currentTimeMillis();
    final HttpResponse response = client.execute(httpGet);
    final long tsEnd = System.currentTimeMillis();
    if (response.getStatusLine().getStatusCode() != 200) {
      throw new ApiCallException(String.format("Got non-200 HTTP status code "
              + "[%d - %s].", response.getStatusLine().getStatusCode(),
              response.getStatusLine().getReasonPhrase()));
    }
    System.out.println("Received response from server. Deserialising...");
    final Gson gson = new Gson();
    final List<LocationInfo> result;
    try (final InputStream is = response.getEntity().getContent();
            final InputStreamReader isr = new InputStreamReader(is)) {
      final LocationInfo[] deserDat = gson.fromJson(isr, LocationInfo[].class);
      result = Arrays.asList(deserDat);
    }
    System.out.println(String.format("Deserialised successfully. Found %d "
            + "location info items. API Call took %d millis.", result.size(),
            (tsEnd - tsBegin)));
    return result;
  }
}
