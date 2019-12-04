package com.consumer;

import java.net.URISyntaxException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ConsumerMain {

  public static void main(String[] args) throws URISyntaxException {
    SpringApplication.run(ConsumerMain.class,args);
  }
}
