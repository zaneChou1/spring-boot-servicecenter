package com.provider;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class helloControl {
  @RequestMapping("/")
  public String providerHello() {
    return "Hello Spring-Boot-ServiceCenter !";
  }
}
