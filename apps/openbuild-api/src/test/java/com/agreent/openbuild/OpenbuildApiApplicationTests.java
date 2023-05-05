package com.agreent.openbuild;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.containers.RabbitMQContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

@Testcontainers
@SpringBootTest
class OpenbuildApiApplicationTests {

  @Container
  static MySQLContainer mySQLContainer =
      new MySQLContainer<>(DockerImageName.parse("mysql:8.0-debian"));

  @Container
  static RabbitMQContainer rabbitMQContainer =
      new RabbitMQContainer(DockerImageName.parse("rabbitmq:3-management" + "-alpine"));

  @BeforeAll
  public static void setUp() {
    mySQLContainer.withReuse(true);
    rabbitMQContainer.withReuse(true);

    mySQLContainer.start();
    rabbitMQContainer.start();
  }

  @DynamicPropertySource
  static void setupProperties(DynamicPropertyRegistry registry) {
    registry.add("spring.datasource.url", () -> mySQLContainer.getJdbcUrl());
    registry.add("spring.datasource.driverClassName", () -> mySQLContainer.getDriverClassName());
    registry.add("spring.datasource.username", () -> mySQLContainer.getUsername());
    registry.add("spring.datasource.password", () -> mySQLContainer.getPassword());
    registry.add("spring.rabbitmq.host", () -> rabbitMQContainer.getHost());
    registry.add("spring.rabbitmq.port", () -> rabbitMQContainer.getAmqpPort());
    registry.add("spring.rabbitmq.username", () -> rabbitMQContainer.getAdminUsername());
    registry.add("spring.rabbitmq.password", () -> rabbitMQContainer.getAdminPassword());
  }

  @AfterAll
  public static void tearDown() {
    mySQLContainer.stop();
    rabbitMQContainer.stop();
  }

  @Test
  void contextLoads() {
    mySQLContainer.isRunning();
    rabbitMQContainer.isRunning();
  }
}
