package com.donavon.backend.exception;

public class DupUsernameException extends RuntimeException {
  public DupUsernameException() {
    super("This username is already taken.");
  }
}
