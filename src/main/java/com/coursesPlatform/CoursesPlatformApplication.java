package com.coursesPlatform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@SpringBootApplication
public class CoursesPlatformApplication {

    public static void main(String[] args) {
        SpringApplication.run(CoursesPlatformApplication.class, args);
    }

}
