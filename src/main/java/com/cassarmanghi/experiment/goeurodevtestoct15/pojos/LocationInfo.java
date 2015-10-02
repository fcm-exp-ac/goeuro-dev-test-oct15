package com.cassarmanghi.experiment.goeurodevtestoct15.pojos;

import com.google.gson.annotations.SerializedName;

public class LocationInfo {

  private static class JsonKeys {

    private JsonKeys() {
    }
    public static final String ID = "__id";
    public static final String KEY = "key";
    public static final String NAME = "name";
    public static final String FULL_NAME = "fullName";
    public static final String IATA_AIRPORT_CODE = "iata_airport_code";
    public static final String TYPE = "type";
    public static final String COUNTRY = "country";
    public static final String GEO_POSITION = "geo_position";
    public static final String LOCATION_ID = "locationId";
    public static final String IN_EUROPE = "inEurope";
    public static final String COUNTRY_CODE = "countryCode";
    public static final String CORE_COUNTRY = "coreCountry";
    public static final String DISTANCE = "distance";
  }

  @SerializedName(JsonKeys.ID)
  private long id;
  @SerializedName(JsonKeys.KEY)
  private Object key;
  @SerializedName(JsonKeys.NAME)
  private String name;
  @SerializedName(JsonKeys.FULL_NAME)
  private String fullName;
  @SerializedName(JsonKeys.IATA_AIRPORT_CODE)
  private String iataAirportCode;
  @SerializedName(JsonKeys.TYPE)
  private String type;
  @SerializedName(JsonKeys.COUNTRY)
  private String country;
  @SerializedName(JsonKeys.GEO_POSITION)
  private GeoPosition geoPosition;
  @SerializedName(JsonKeys.LOCATION_ID)
  private long locationId;
  @SerializedName(JsonKeys.IN_EUROPE)
  private boolean inEurope;
  @SerializedName(JsonKeys.COUNTRY_CODE)
  private String countryCode;
  @SerializedName(JsonKeys.CORE_COUNTRY)
  private boolean coreCountry;
  @SerializedName(JsonKeys.DISTANCE)
  private Object distance;

  public long getId() {
    return id;
  }

  public Object getKey() {
    return key;
  }

  public String getName() {
    return name;
  }

  public String getFullName() {
    return fullName;
  }

  public String getIataAirportCode() {
    return iataAirportCode;
  }

  public String getType() {
    return type;
  }

  public String getCountry() {
    return country;
  }

  public GeoPosition getGeoPosition() {
    return geoPosition;
  }

  public long getLocationId() {
    return locationId;
  }

  public boolean isInEurope() {
    return inEurope;
  }

  public String getCountryCode() {
    return countryCode;
  }

  public boolean isCoreCountry() {
    return coreCountry;
  }

  public Object getDistance() {
    return distance;
  }
}
