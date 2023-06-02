package com.example.demo.config;
import com.example.demo.containers.PostgresTestContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
public class ContainerEnvironment {

    @Container
    public static PostgreSQLContainer postgreSQLContainer = PostgresTestContainer.getInstance();

}
