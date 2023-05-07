package com.agreent.openbuild;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"com.agreent.openbuild", "org.axonframework", "engineering.everest"})
@EntityScan(basePackages = {"com.agreent.openbuild", "org.axonframework", "engineering.everest"})
public class OpenbuildApiApplication {

  public static void main(String[] args) {
    SpringApplication.run(OpenbuildApiApplication.class, args);
  }
}
