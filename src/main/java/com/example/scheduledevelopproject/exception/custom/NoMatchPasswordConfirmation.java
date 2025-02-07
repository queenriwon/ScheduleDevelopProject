package com.example.scheduledevelopproject.exception.custom;

public class NoMatchPasswordConfirmation extends RuntimeException {
  public NoMatchPasswordConfirmation(String message) {
    super(message);
  }
}
