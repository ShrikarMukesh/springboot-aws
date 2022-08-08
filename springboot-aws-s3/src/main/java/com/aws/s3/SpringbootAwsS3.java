package com.aws.s3;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.aws.autoconfigure.context.ContextStackAutoConfiguration;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication(exclude = {ContextStackAutoConfiguration.class})
@RestController
public class SpringbootAwsS3 {

    Logger logger= LoggerFactory.getLogger(SpringbootAwsS3.class);
    public static void main(String[] args) {
        SpringApplication.run(SpringbootAwsS3.class, args);
    }
}
