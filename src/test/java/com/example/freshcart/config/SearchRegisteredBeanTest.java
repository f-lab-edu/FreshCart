package com.example.freshcart.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
public class SearchRegisteredBeanTest {

  @Autowired
  private ApplicationContext applicationContext;

  @Test
  public void contextLoads() throws Exception {
    if (applicationContext != null) {
      String[] beans = applicationContext.getBeanDefinitionNames();

      for (String bean : beans) {
        System.out.println("bean : " + bean);
      }
    }
  }
}