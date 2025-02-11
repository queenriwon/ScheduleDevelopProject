package com.example.scheduledevelopproject.exception.custom.badrequest;

import com.example.scheduledevelopproject.exception.custom.BadRequestException;

public class NoMatchPasswordConfirmation extends BadRequestException {
  public NoMatchPasswordConfirmation(String message) {
    super(message);
  }
}
