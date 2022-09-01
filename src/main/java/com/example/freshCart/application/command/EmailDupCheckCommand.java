package com.example.freshCart.application.command;

public class EmailDupCheckCommand {
  private String email;

  public EmailDupCheckCommand(String email) {
    this.email = email;
  }

  public String getEmail() {
    return email;
  }
}
