package com.example.scheduledevelopproject.exception.custom;

public class UnauthorizedCommentAccessException extends RuntimeException {
  public UnauthorizedCommentAccessException(String message) {
    super(message);
  }
}
