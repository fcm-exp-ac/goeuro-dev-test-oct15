package com.cassarmanghi.experiment.goeurodevtestoct15.pojos;

import com.google.gson.annotations.SerializedName;

public class GeoPosition {

  private static class JsonKeys {

    private JsonKeys() {
    }
    public static final String LATITUDE = "latitude";
    public static final String LONGITUDE = "longitude";
  }

  @SerializedName(JsonKeys.LATITUDE)
  private long latitude;
  @SerializedName(JsonKeys.LONGITUDE)
  private long longitude;

  public long getLatitude() {
    return latitude;
  }

  public long getLongitude() {
    return longitude;
  }
}
