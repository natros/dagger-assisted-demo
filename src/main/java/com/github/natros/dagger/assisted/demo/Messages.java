package com.github.natros.dagger.assisted.demo;

import javax.inject.Inject;

public class Messages {
  @Inject
  Messages() {}

  public void log(String msg) {
    System.out.println(msg);
  }
}
