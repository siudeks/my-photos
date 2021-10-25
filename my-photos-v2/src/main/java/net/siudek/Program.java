package net.siudek;

import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;

@QuarkusMain
public class Program implements QuarkusApplication {

  @Override
  public int run(String... args) {
    System.out.println("Hello world 2");
    return 0;
  }
}
