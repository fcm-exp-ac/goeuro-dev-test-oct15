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
  private double latitude;
  @SerializedName(JsonKeys.LONGITUDE)
  private double longitude;

  public double getLatitude() {
    return latitude;
  }

  public double getLongitude() {
    return longitude;
  }
}
