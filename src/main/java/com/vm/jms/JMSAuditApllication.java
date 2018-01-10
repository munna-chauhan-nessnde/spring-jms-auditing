package com.vm.jms;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class JMSAuditApllication {

    public static void main(String[] args) {
        SpringApplication.run(JMSAuditApllication.class, args);
        /*new SpringApplicationBuilder()
                .sources(JMSAuditApllication.class)
                .bannerMode(Banner.Mode.OFF)
                .run(args);*/
    }
}
