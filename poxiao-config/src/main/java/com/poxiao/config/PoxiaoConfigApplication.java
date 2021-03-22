package com.poxiao.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@EnableDiscoveryClient
@SpringBootApplication
public class PoxiaoConfigApplication {

    public static void main(String[] args) {
        SpringApplication.run(PoxiaoConfigApplication.class, args);
    }

}
