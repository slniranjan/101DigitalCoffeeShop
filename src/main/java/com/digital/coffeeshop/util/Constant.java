package com.digital.coffeeshop.util;

public class Constant {

  private Constant(){
    throw new IllegalStateException("Utility class");
  }

  public static final String PRODUCE_TYPE = "application/json";

  public static final String FIELD_ERROR_SEPARATOR = ": ";
  public static final String PATH = "path";
  public static final String ERRORS = "error";
  public static final String STATUS = "status";
  public static final String MESSAGE = "message";
  public static final String TIMESTAMP = "timestamp";
  public static final String TYPE = "type";
  public static final String LIST_JOIN_DELIMITER = ",";
  public static final String ERRORS_FOR_PATH = "errors {} for path {}";
  public static final String ACCESS_DENIED = "Access denied!";
  public static final String INVALID_REQUEST = "Invalid request";
}
