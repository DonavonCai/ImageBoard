package com.donavon.backend.exception;

public class DupEmailException extends RuntimeException {
  public DupEmailException() {
    super("This email is already taken.");
  }
}
