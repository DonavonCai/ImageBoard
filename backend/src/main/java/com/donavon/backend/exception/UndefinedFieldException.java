package com.donavon.backend.exception;

public class UndefinedFieldException extends RuntimeException {
  private String field;

  public UndefinedFieldException() {
    super("Undefined field.");
  }

  public UndefinedFieldException(String field) {
    super(field + " is not recognized as a field.");
    this.field = field;
  }

  public String getField() {
    return this.field;
  }
}
