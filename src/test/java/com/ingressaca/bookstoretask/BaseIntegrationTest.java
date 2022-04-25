package com.ingressaca.bookstoretask;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@SpringBootTest(classes = BookStoreTaskApplication.class)
@TestPropertySource(
        locations = "classpath:application-test.properties")
public @interface BaseIntegrationTest {
}
