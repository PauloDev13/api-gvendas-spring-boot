package com.gvendas.gestaogvendas.exceptions;

public class ErrorHandler {
  private final String userMessage;
  private final String developerMessage;

  public ErrorHandler(String userMessage, String developerMessage) {
    this.userMessage = userMessage;
    this.developerMessage = developerMessage;
  }

  public String getUserMessage() {
    return userMessage;
  }

  public String getDeveloperMessage() {
    return developerMessage;
  }
}
