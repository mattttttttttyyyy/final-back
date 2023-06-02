package com.example.demo;

import com.example.demo.FinalApplication;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.utility.DockerImageName;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(
        classes = FinalApplication.class,
        webEnvironment = WebEnvironment.RANDOM_PORT,
        properties = { "spring.datasource.url=jdbc:tc:postgresql:11-alpine:///databasename" }
)
@ActiveProfiles("test")
public abstract class TestWithTestcontainers {

    static GenericContainer<?> redis = new GenericContainer<>(DockerImageName.parse("redis:3-alpine"))
            .withExposedPorts(6379);

    @DynamicPropertySource
    static void redisProperties(DynamicPropertyRegistry registry) {
        redis.start();
        registry.add("spring.redis.host", redis::getHost);
        registry.add("spring.redis.port", redis::getFirstMappedPort);
    }
}