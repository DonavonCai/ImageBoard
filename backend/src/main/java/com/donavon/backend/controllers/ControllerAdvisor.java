package com.donavon.backend.controllers;

import com.donavon.backend.exception.DupUsernameException;
import com.donavon.backend.exception.UndefinedFieldException;

import java.util.LinkedHashMap;
import java.util.Map;

import com.donavon.backend.exception.DupEmailException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {
  @ExceptionHandler(DupUsernameException.class)
  public ResponseEntity<Object> handleDupUsernameException(DupUsernameException ex, WebRequest req) {
      Map<String, Object> body = new LinkedHashMap<>();
      body.put("field", "username");
      body.put("message", "Username is taken.");

      return new ResponseEntity<>(body, HttpStatus.CONFLICT);
  }

  @ExceptionHandler(DupEmailException.class)
  public ResponseEntity<Object> handleDupEmailException(DupEmailException ex, WebRequest req) {
      Map<String, Object> body = new LinkedHashMap<>();
      body.put("field", "email");
      body.put("message", "Email is taken.");

      return new ResponseEntity<>(body, HttpStatus.CONFLICT);
  }

  @ExceptionHandler(UndefinedFieldException.class)
  public ResponseEntity<Object> handleUndefinedFieldException(UndefinedFieldException ex, WebRequest req) {
    Map<String, Object> body = new LinkedHashMap<>();
    body.put("field", ex.getField());
    body.put("message", ex.getField() + " is not recognized as a field.");

    return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
  }
}
