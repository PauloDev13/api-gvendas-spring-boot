package com.gvendas.gestaogvendas.exceptions;

public class ClienteNotFoundException extends RuntimeException {
  public ClienteNotFoundException(String message) {
    super(message);
  }
}
