package com.example.demo.containers;

import jakarta.persistence.OneToMany;
import org.testcontainers.containers.PostgreSQLContainer;

public class PostgresTestContainer extends PostgreSQLContainer<PostgresTestContainer> {
    public static final String IMAGE_VERSION = "postgres:11.1";
    public static PostgresTestContainer container;
    public static final  String DATABASE_NAME = "test";
    public PostgresTestContainer() {
        super(IMAGE_VERSION);
    }

    public static PostgresTestContainer getInstance() {
        if (container == null) {
            container = new PostgresTestContainer().withDatabaseName(DATABASE_NAME);
        }
        return container;
    }

    @Override
    public void   start() {
        super.start();
        System.setProperty("DB_URL", container.getJdbcUrl());
        System.setProperty("DB_USERNAME", container.getUsername());
        System.setProperty("DB_PASSWORD", container.getPassword());
    }

    @Override
    public void stop() {
    }
}